#!/bin/sh

export SPRING_PROFILES_ACTIVE="local"
export LOCALHOST_KEYSTORE_PASSWORD=$(keepassxc-cli show -s -a "Password" -k /media/$USER/primary/keys/keepassxc.keyfile ~/Documents/keepassxc.kdbx "/Development/users-api - localhost.p12 keystore")
