## Getting Started

This project is a Command-Line Interface (CLI) tool designed to test the availability of websites or services. It supports multiple URLs and provides functionalities like fetching statuses, monitoring availability over intervals, and maintaining historical data.

## Prerequisites:

Java 17 or later
Kotlin (if using Kotlin)
Git (https://github.com/tiagomcunha1999/SRE_Challenge)


## Steps

 - Clone the repository (optional):
        git clone https://github.com/yourusername/SRE_Challenge.git
        cd SRE_Challenge
 
 - Place the desired urls to be tested in the "urls.txt" file located in the resources folder within the "sre_challenge" project
    ex: 
        "http://example.com
        https://www.google.com/
        http://example.orgergfdg"

 - Run the project, using terminal: 
        cd src
        java Main
    
## Features

 - "fetch" 
        Query the status of all configured sites or services.

 - "live"
       Query the URLs of all configured site(s) or service(s) based on a range. 
       The default time is 5 seconds.

 - "history" 
        Display in a table format all data collected in the database.

 - "backup"
        Takes a file path argument and creates a backup of the data in the current database.

 - "restore"
        Takes a file path argument and restores the data in the file to the current database.


## Commands

 - java Main 
    This functionality will automatically execute the fetch, live and history classes.
    Configurable query interval with a reasonable default value: 5 seconds for the live checking.

 - java Main fetch  
    Save output to a database .
    Display the result of the websites or services.

 - java Main live interval <- the last argument defines the time interval
    Save results to a database.
    Configurable query interval.
    ex: java Main live 2

 - java Main history  
    Display in a table format all data collected in the database.

 - java Main backup  filepath
    Takes a file path argument and creates a backup of the data in the current database.
    We can choose in the code what we want:
        Save to a simple .txt file with a custom format.
        Save to a simple .csv file, with comma-separated row data.
        Serialize the objects in our database and save to a file.
    ex: java Main backup "C:\Users\hp\Desktop\backup.txt"

 - java Main restore filepath
    Takes a file path argument and restores the data in the file to the current database.
    Merge restored data into current database instead of overwriting it
    Validate that the file is a valid backup file/protect against invalid files.
    ex: ex: java Main restore "C:\Users\hp\Desktop\backup.txt"


The `JAVA PROJECTS` view allows you to manage your dependencies. More details can be found [here](https://github.com/microsoft/vscode-java-dependency#manage-dependencies).
