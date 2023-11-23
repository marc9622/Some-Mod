{ pkgs ? import <nixpkgs> {} }:
let
  gradleCli = "128m";
  gradleDaemon = "1024m"; #"700m";
in
pkgs.mkShell {
  buildInputs = with pkgs; [
    flite
  ];

  shellHook = ''
    export GRADLE_OPTS="-Xmx${gradleCli} -Dorg.gradle.jvmargs='-Xmx${gradleDaemon}'"
    export PATH="$PATH:${pkgs.flite}/lib"
  '';
}
