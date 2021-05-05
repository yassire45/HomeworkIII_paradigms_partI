from zeep import Client
from pyfiglet import Figlet
from termcolor import colored
import base64

f = Figlet(font='slant')
serviceimpl = Client('src/main//resources/ServiceImplService.wsdl').service
def main_menu():
    while True:
        print(colored(f.renderText('Homework 2'), 'blue'))
        print(colored('Welcome to Homework 2', 'green'))
        print(colored('1.', 'yellow'), 'Get OsName.')
        print(colored('2.', 'yellow'), 'Get Screenshot.')
        print(colored('3.', 'yellow'), 'Reboot System.')
        print(colored('4.', 'yellow'), 'Exit.')
        choice = input(colored('\n--> Enter your Selection: ', 'blue'))
        if choice == '1':
            print(serviceimpl.getOsName()) # Function 1
        elif choice == '2': # Function 2
            base64url = serviceimpl.getScreenShot()
            outputpath = "C:\\Users\\yassi\\Downloads\\saved.png"
            with open(outputpath, 'wb') as fh:
                fh.write(base64.b64decode(base64url))
            print("Done.")
        elif choice == '3':
            serviceimpl.reboot() # Function 3
        elif choice =='4':
            return
        else:
            print(colored('--> Please select from the Menu.', 'red'))

main_menu()