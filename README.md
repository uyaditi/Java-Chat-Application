# Java Chat Application

This repository hosts a chat application developed in Java, leveraging socket programming, Swing GUI, JDBC concepts, and encryption algorithms. The application enables real-time messaging through socket connections, ensuring seamless communication between users with an intuitive Swing-based interface.

Additionally, it features a login/register system implemented using JDBC for user authentication.

## Folder Structure

Inside the `src` folder:

1. **Encryption**: Contains subfolders for various encryption algorithms (symmetric, asymmetric, hash, keyExchange) with source codes and example usage.
2. **UI**: Holds the main screens of the application using Swing and logic for socket programming.
3. **Files**: `ChatClientCLI.java` and `ChatServerCLI.java` are helper classes for communication.
4. `EncryptionLibrary.jar` in `src/Encryption`: A library file to directly access encryption algorithms.

## Screenshots
Screenshots / demo of the project can be found [here](https://drive.google.com/drive/folders/1JmLSesJ1kxf4ldgzfTuEB0y7DotUNfTk?usp=sharing)

## Instructions to Run the Project

To run the project, follow these steps:

1. Install Eclipse IDE and configure it (resource: [Eclipse Downloads](https://www.eclipse.org/downloads/)).
2. Download this folder and open it as a new Eclipse project.
3. Ensure all necessary dependencies are resolved.
4. Run the ServerUI.java file to start the server. Once server is running on any port run the ClientUI.java to connect clients to the server.
5. Multiple clients can be connected to the server. You can now chat with everyone else in the room. 

## Note

This is an original project developed from scratch and is not sourced from the internet or any existing repository.

