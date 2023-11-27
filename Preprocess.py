import os

# Define a function to process a single line
def process_line(line):
    date = line[15:23]
    station_id = line[4:9]
    temperature = line[88:93] if line[88:93] != '+99999' else None
    wind_speed = line[65:69] if line[65:69] != '99999' else None
    pressure = line[99:104] if line[99:104] != '99999' else None
    sky_cover = line[71:75] if line[71:75] != '99999' else None
    if None in [temperature, wind_speed, pressure, sky_cover]:
        return None
    return date, station_id, temperature, wind_speed, pressure, sky_cover

# Directory containing your text files
input_directory = 'C:/User/mohamed/Documents/hadoop_project/weather_data'
# Directory where you want to save the processed data
output_directory = 'C:/User/mohamed/Documents/hadoop_project/Output'

# Get a list of all files in the directory
files = os.listdir(input_directory)

# Iterate over each file in the directory
for filename in files:
    with open(os.path.join(input_directory, filename), 'r') as file:
        lines = file.readlines()

    # Process each line in the file
    data = [process_line(line) for line in lines]
    data = [d for d in data if d is not None]  # Filter out None values

    # Create a new file with processed data
    output_filename = os.path.splitext(filename)[0] + "_processed.txt"
    with open(os.path.join(output_directory, output_filename), 'w') as output_file:
        for date, station_id, temperature, wind_speed, pressure, sky_cover in data:
            output_file.write(f"Date: {date}, Station ID: {station_id}, Temperature: {temperature}, Wind Speed: {wind_speed}, Pressure: {pressure}, Sky Cover: {sky_cover}\n")

    print(f"Processed {filename} and saved as {output_filename}")