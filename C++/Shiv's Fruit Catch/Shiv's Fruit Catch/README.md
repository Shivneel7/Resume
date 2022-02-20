# Installation Instructions

## Windows Users

This project can only run in your Linux subsystem (Ubuntu Shell), not natively in Windows.

- Update your apt repositories: `sudo apt update`
- Install freeglut: `sudo apt install freeglut3-dev`
- Install xming (https://sourceforge.net/projects/xming/)
- Set display environment variable: `echo "export DISPLAY=localhost:0.0" >> ~/.bashrc`
- On WSL 2: Display Environment variable should instead be `$(route.exe print | grep 0.0.0.0 | head -1 | awk '{print $4}'):0.0`
- Relaunch your VS Code

## Linux Users

If you are running Linux natively (not the Ubuntu Shell under Windows)

- Update your apt repositories: `sudo apt update`
- Install freeglut: `sudo apt install freeglut3-dev`

## Mac Users

On a Mac, the project just works

# Running the App

After all dependencies above have been installed, navigate to the project folder and:

- Compile: `make`

- Run: `./glutapp`
