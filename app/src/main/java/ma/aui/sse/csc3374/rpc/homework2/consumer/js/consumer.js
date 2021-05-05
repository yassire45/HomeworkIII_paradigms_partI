let soap = require('soap');
global.atob = require("atob");

const chalk = require('chalk');
var fs = require('fs');
const prompt = require('prompt-sync')({sigint: true});
const path = require('path');
let url = 'http://localhost:9000/ServiceImpl?wsdl';

function getOS() {
    return new Promise((resolve, reject) => {
        soap.createClient(url, (err, client) => {
            client.getOsName((err,res) => {
                res ? resolve(res.return) : reject(err);
            })
        })
    })
}
function getScreenShot() {
    return new Promise((resolve, reject) => {
        soap.createClient(url, (err, client) => {
            client.getScreenShot((err,res) => {
                res ? resolve(res.return) : reject(err);
            })
        })
    })
}

function reboot() {
    return new Promise((resolve, reject) => {
        soap.createClient(url, (err, client) => {
            client.reboot((err,res) => {
                res ? resolve(res.return) : reject(err);
            })
        })
    })
}

async function menu() {
    
    let OsName, ScreenShotPath;
    
   
    while (true) {
    console.log("\n1. GET OsName\n2. SAVE ScreenShot\n3. REBOOT\n4. Exit\nChoice: ");
    let choice = prompt()

    if (parseInt(choice) === 1) {

        OsName = await getOS();
        console.log(chalk.red('OS Name: '), chalk.red(OsName));

    } else if (parseInt(choice) === 2) {

        const SC = await getScreenShot();
        ScreenShotPath = __dirname + '\\screenshot.png';
        console.log(chalk.green("File was successfully saved in: "), chalk.green(ScreenShotPath));
        fs.writeFile(ScreenShotPath, global.atob(SC), 'binary', function(err) { 
            console.log("DONE"); 
        });

    } else if (parseInt(choice) === 3) {
        await reboot();
    } 
    else if (parseInt(choice) === 4) {
        return;
    }else {
        console.log("Please enter a valid option");
    }
}
}
menu()