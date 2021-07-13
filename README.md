# Capital One Assignment
## _File System Manager_

[![Build Status](https://travis-ci.org/joemccann/dillinger.svg?branch=master)](https://travis-ci.org/joemccann/dillinger)

### Discription
Design a simple file system manager to manage allocation for storing, retrieving
and deleting files on the storage device.

Storage devices (e.g. hard drive, SSD drive, flash drive) have capacity/size of X (MB, GB)
divided in blocks of Y (KB). You can only write or read a whole block. For example, if the hard
disk has a size of 1 MB and block size of 1KB, it will have 1024 blocks total and a file that's
1200 bytes will take 2 blocks.

You have to design a file system manager that will enable you to save (given fileID and size in
bytes), delete file (given fileID) and read file (given fileID). Save will return a list of blocks that

OS can use to store the file to, delete will have to mark blocks free and read file will return list of blocks for a given file.

### About
Language - Java
This project has a simple console based UI and takes in commands that let you manipulate the file system.
You will first be prompted for the drive szie in MB, then the block size in KB
These are the following commands that you can use.
Commands:--
- insert [file name] [size in KB]
- remove [file name]
- read [file name]
- printll --to print the Doubly LinkedList
- printmemory --to print memory array
- exit --to exit the application

### Classes Used

- Main.java
- UI.java
- MemManager.java
- FreeblockArray.java
- MemoryArray.java

### How it Works (Simple Explaination)
When you run the project objects of the MemManager, FreeblockArray, and MemoryArray are created and initialized. 

The class MemoryArray is initalized based on your input of drive size (MB) and block size (KB).The MemoryArray is the actual pool of memory and it is managed by the MemManger that allows you to manioulate the array. The class FreeblockArray is used to keep track of block that are currently in use and not in use.

When the UI sends a command to the MemManger, it will check the FreeblockArray and then manipulate the MemoryArray.

The FreeblockArray uses a Doubly Linked List, and the Memory uses a simple integer array.

### How to Run Project
Unzip the folder and run the Main.java

### Possible Improvments
Since the discription indicated to make even blocks based off of the block size, it was simpler just to use an integer array to represent the memory pool.

However, if it was asked to make an uneven distrubution of blocks of different sizes, I would use a Min heap for the memory pool. Then use the search algorithm, best fit, to find the blocks to distribute the memory when the insert command was invoked.
