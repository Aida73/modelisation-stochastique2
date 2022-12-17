import os

path = "/Users/user/Desktop/travailFinal/data_per_day"
files = [f for f in os.listdir(
    path) if f.endswith(".csv")]

for i in range(len(files)):
    os.rename(os.path.join(path, files[i]), os.path.join(
        path, f"2014-day-{i+1}.csv"))
