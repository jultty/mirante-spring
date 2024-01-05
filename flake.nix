{
  inputs = { nixpkgs.url = "github:NixOS/nixpkgs/nixos-23.11"; };
  outputs = { self, nixpkgs }: 
    let 
    system = "x86_64-linux";
    pkgs = import nixpkgs { 
      inherit system; 
    };
  in
  {
    devShells.x86_64-linux.default = pkgs.mkShell {
      packages = [
        pkgs.jdk21
        pkgs.maven
      ];
      DEBUG = 1;
    };
  };
}
