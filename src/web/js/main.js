const exerciseContainer = document.getElementById('exerciseContainer');
const exerciseSetDropdown = document.getElementById('exerciseSet');
var uniqueSets;
var options;

// get the exercise sets from the backend
fetch('http://localhost:8888/option')
  .then(response => response.json())
  .then(data => {
    options = data;
    uniqueSets = extractExerciseSets(data);

    uniqueSets.forEach(set => {
      const optionElement = document.createElement('option');
      optionElement.value = set.id;
      optionElement.textContent = set.name;
      exerciseSetDropdown.appendChild(optionElement);
    });
  })

// listen for a selection of an exercise set
exerciseSetDropdown.addEventListener('input', function () {
  const selectedSetId = exerciseSetDropdown.value;
  const selectedSet = uniqueSets.find(set => set.id === selectedSetId);

  if (selectedSet) {
    const setOptions = filterOptionsByExerciseSet(options, selectedSet.id);
    const exercises = assembleExerciseOptions(setOptions)
    populateExerciseContainer(exercises);

    exerciseContainer.addEventListener('submit', function (event) {
      event.preventDefault();

      const form = document.querySelector('form');
      const checkboxes = Array.from(form.querySelectorAll('input[type="checkbox"]'));

      const selectedOptions = checkboxes
      .filter(checkbox => checkbox.checked)
      .map(checkbox => checkbox.value);

      printCorrectOptions(selectedOptions, setOptions);
      sendResponseEvent(selectedOptions);
      sendResponseResult(selectedOptions);
    });

  }
});

function sendResponseResult(selection) {

  const xhr = new XMLHttpRequest();
  xhr.open('POST', 'http://localhost:8888/result');
  xhr.setRequestHeader('Content-Type', 'application/json');

  const result = {
    time: new Date().getTime(),
    set: {
      id: exercise_set_id,
      name: exercise_set_name,
      access: exercise_set_access,
    }
  };

  xhr.send(JSON.stringify(result));
  console.dir(result);
}

function sendResponseEvent(selection) {

  const xhr = new XMLHttpRequest();
  xhr.open('POST', 'http://localhost:8888/event');
  xhr.setRequestHeader('Content-Type', 'application/json');

  const event = {
    description: 'exercise set response event',
    content: selection.toString(),
    time: new Date().getTime(),
  };

  xhr.send(JSON.stringify(event));
  console.dir(event);
}

function printCorrectOptions(selection, setOptions) {

  let correctCount = 0;
  let incorrectCount = 0;

  selection.forEach(selectedOption => {
    const option = setOptions.find(option => option.id === selectedOption);
    if (option && option.correct) {
      correctCount++;
    } else {
      incorrectCount++;
    }
  });

  console.log(`Correctly Checked: ${correctCount} Incorrectly Checked: ${incorrectCount}`);
}

function assembleExerciseOptions(options) {
  const exerciseOptionsMap = {};

  options.forEach(option => {
    const exerciseId = option.exercise_id.id;
    const exerciseInstruction = option.exercise_id.instruction;

    if (!exerciseOptionsMap[exerciseId]) {
      exerciseOptionsMap[exerciseId] = {
        id: exerciseId,
        instruction: exerciseInstruction,
        options: [],
      };
    }

    exerciseOptionsMap[exerciseId].options.push(option);
  });

  return Object.values(exerciseOptionsMap);
}

function populateExerciseContainer(exercises) {

  exerciseContainer.innerHTML = '';
  const form = document.createElement('form');
  exerciseContainer.appendChild(form);

  exercises.forEach(exercise => {
    // Create a div for each exercise
    const div = document.createElement('div');

    // Append an <h3> element for the exercise ID
    const h3Element = document.createElement('h3');
    h3Element.textContent = `Exercise ID: ${exercise.id}`;
    div.appendChild(h3Element);

    // Create and append a paragraph for the exercise instruction
    const pElement = document.createElement('p');
    pElement.textContent = `${exercise.instruction}`;
    div.appendChild(pElement);

    // Iterate through each option in the exercise and create checkboxes
    exercise.options.forEach(option => {
      const checkboxElement = document.createElement('input');
      checkboxElement.setAttribute('type', 'checkbox');
      checkboxElement.setAttribute('name', `exercise_${exercise.id}`);
      checkboxElement.setAttribute('value', option.id);

      const labelElement = document.createElement('label');
      labelElement.textContent = option.content;

      div.appendChild(checkboxElement);
      div.appendChild(labelElement);

      // Add a line break for readability
      div.appendChild(document.createElement('br'));
    });

    form.appendChild(div);
  });

  // Add a submit button
  const submitButton = document.createElement('button');
  submitButton.setAttribute('type', 'submit');
  submitButton.setAttribute('id', 'submit');
  submitButton.textContent = 'Submit';
  form.appendChild(submitButton);
}


function filterOptionsByExerciseSet(fullOptions, targetExerciseSetId) {
  return fullOptions.filter(option => {
    return (
      option.exercise_id &&
        option.exercise_id.set_id &&
        option.exercise_id.set_id.id === targetExerciseSetId
    );
  });
}

function extractExerciseSets(data) {
  const uniqueExerciseSets = new Set();

  data.forEach(option => {
    if (option.exercise_id && option.exercise_id.set_id) {
      const setId = option.exercise_id.set_id.id;
      const setName = option.exercise_id.set_id.name;

      if (!uniqueExerciseSets[setId]) {
        uniqueExerciseSets[setId] = { id: setId, name: setName };
      }
    }
  });

  return Object.values(uniqueExerciseSets);
}

