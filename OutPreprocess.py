import os
from tkinter import filedialog
from tkinter import Tk



root = Tk()
root.withdraw()

# Ask user to select a directory
dir_path = filedialog.askdirectory()

# Ask user to select text files to process
file_paths = filedialog.askopenfilenames(initialdir=dir_path, title="Select text files", filetypes=(("Text files", "*.txt"),))

for file_path in file_paths:
    with open(file_path, 'r') as f:
        lines = f.readlines()

    with open(os.path.join(dir_path, 'output_preprocessed.txt'), 'w') as f:
        for line in lines:
            if '99999' in line or '+99999' in line or '999':
                continue
            f.write(line)
