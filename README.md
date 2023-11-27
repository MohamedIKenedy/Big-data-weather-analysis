# Big-data-weather-analysis

This project focuses on extracting valuable insights from the NOAA weather dataset using Hadoop, HDFS (Hadoop Distributed File System), and MapReduce. The goal is to compute and analyze key weather metrics such as average, maximum, and minimum values for the following parameters:

- Temperature
- Wind Speed
- Sky cloud cover
- Pressure

## Project Workflow

1. **Data Extraction and Processing**:
   - Utilized Hadoop and HDFS to manage and process the large NOAA weather dataset efficiently.
   - Implemented MapReduce jobs to extract relevant information and compute the desired weather metrics.
   
2. **Weather Metric Computation**:
   - Calculated the average, maximum, and minimum values for temperature, wind speed, sky cloud cover, and pressure across the dataset.
   - Employed MapReduce to perform parallel computations, handling massive volumes of weather data.

3. **Data Visualization**:
   - Utilized Power BI for visualizing and presenting the computed weather metrics.
   - Created intuitive and informative visualizations to showcase trends and patterns in the weather data.

## Usage

1. **Hadoop and MapReduce Setup**:
   - Set up Hadoop and configure HDFS for managing the dataset.
   - Write and execute MapReduce jobs for calculating the desired weather metrics.

2. **Visualization using Power BI**:
   - Import the computed data into Power BI for creating visualizations.
   - Design and generate insightful charts and graphs to represent the weather trends.

## Folder Structure

- `mapper/`: Contains MapReduce mapper implementations.
- `reducer/`: Contains MapReduce reducer implementations.
- `Weather_data_HadoopDownload.ipynb`: downloading the NOAA weather dataset.

## Steps to Run

1. Load the NOAA weather dataset into the Hadoop cluster.
2. Write MapReduce jobs to compute the average, maximum, and minimum values for temperature, wind speed, sky cloud cover, and pressure.
3. Export the computed data and visualize it using Power BI for insights and trends.

## Credits

This project was developed by Mohamed IFQIR.

## License
Mohamed IFQIR 2023 | All rights Reserved

Feel free to modify, contribute, and use this project while adhering to the specified license terms.
