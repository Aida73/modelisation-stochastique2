import pandas as pd
import os

path_data_months_2014 = "/Users/user/Desktop/ProjetsClasse/Cours-DIC2-GIT/modelisation-stochastique-2/data with VANAD/calls-2014"
files = [f for f in os.listdir(
    path_data_months_2014) if f.endswith(".csv")]


def getMonthDays():
    os.chdir('data_per_day')
    for month in files:
        data = pd.read_csv(f'{path_data_months_2014}/{month}')
        # split time into date and hours
        data[['date_received_date', 'date_received_heure']
             ] = data['date_received'].str.split(expand=True)
        data[['hangup_date', 'hangup_heure']
             ] = data['hangup'].str.split(expand=True)
        data[['answered_date', 'answered_heure']
             ] = data['answered'].str.split(expand=True)
        data[['consult_date', 'consult_heure']
             ] = data['consult'].str.split(expand=True)
        days = data['date_received_date'].unique()
        for i in range(len(days)):
            data_of_day = data.loc[data['date_received_date'] == days[i]]
            dataframe_day = pd.DataFrame({"date_received_date": data_of_day['date_received_date'], "date_received_heure": data_of_day['date_received_heure'], "queue_name": data_of_day['queue_name'], "agent_number": data_of_day['agent_number'],
                                          "answered_date": data_of_day['answered_date'], "answered_heure": data_of_day['answered_heure'], "consult_date": data_of_day['consult_date'],
                                          "consult_heure": data_of_day['consult_heure'], "transfer": data_of_day['transfer'], "hangup_heure": data_of_day['hangup_heure']})
            dataframe_day.to_csv(f'data_of_day_{days[i]}.csv')


if __name__ == '__main__':
    getMonthDays()
