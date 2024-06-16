{ pkgs ? import <nixpkgs> {} }:
let
  gradleCli = "128m";
  gradleDaemon = "1024m"; #"700m";
in
pkgs.mkShell {
  buildInputs = with pkgs; [
    flite
    glfw
    libglvnd
  ];

  shellHook = ''
    export GRADLE_OPTS="-Xmx${gradleCli} -Dorg.gradle.jvmargs='-Xmx${gradleDaemon}'"
    export PATH="$PATH:${pkgs.flite}/lib"
    export LD_LIBRARY_PATH="''${LD_LIBRARY_PATH}''${LD_LIBRARY_PATH:+:}${pkgs.libglvnd}/lib"
  '';
}
