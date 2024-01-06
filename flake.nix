{
  inputs = { 
    mvn2nix.url = "github:fzakaria/mvn2nix";
    utils.url = "github:numtide/flake-utils";
  };
  outputs = { nixpkgs, mvn2nix, utils, ... }: 
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
        nativeBuildInputs = [ jdk21_headless maven makeWrapper ];
        buildPhase = "${pkgs.maven} package";
        installPhase= ''
          mkdir -p $out/bin; 
          cp ./target/${name}-${version}.jar $out/;
          makeWrapper ${pkgs.jdk21_headless}/bin/java $out/bin/${name} \
            --add-flags "-jar $out/${name}-${version}.jar"
        '';
      };
    

    devShells.${system}.default = pkgs.mkShell {
      packages = [
        pkgs.jdk21
        pkgs.maven
      ];
      DEBUG = 1;
    };
  };
}
