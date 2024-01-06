{
  inputs = { 
    utils.url = "github:numtide/flake-utils";
  };
  outputs = { nixpkgs, utils, ... }: 
    let 
    name = "mirante-spring";
    version = "0.1.1-SNAPSHOT";
    mainClass = "ApiApplication";
    system = "x86_64-linux";
    pkgs = import nixpkgs { 
      inherit system; 
    };
  in
  {
    # Package outputs

    defaultPackage.${system} = 
      with import nixpkgs { system = "${system}"; };
      stdenv.mkDerivation {
        name = "${name}";
        src = self;
        nativeBuildInputs = [ jdk17_headless gradle makeWrapper ];
        buildPhase = "${pkgs.gradle} jar";
        installPhase= ''
          mkdir -p $out/bin; 
          cp ./build/libs/${name}-${version}.jar $out/;
          makeWrapper ${pkgs.jdk17_headless}/bin/java $out/bin/${name} \
            --add-flags "-jar $out/${name}-${version}.jar"
        '';
      };
    

    devShells.${system}.default = pkgs.mkShell {
      packages = [
        pkgs.jdk17
        pkgs.gradle
      ];
    };
  };
}
