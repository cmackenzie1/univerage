import pandas as pd
from scripts.json_to_csv import COLUMNS

DEFAULTS = {
    "terms": ["term", "termtitle", "objectclass"],
    "courses": ["course", "subject", "subjecttitle", "catalog", "coursetitle", "coursedescription", "facultycode",
                "faculty", "departmentcode", "department", "career", "units", "asstring", "objectclass", "term"],
    "classes": ["class", "section", "component", "classtype", "classstatus", "enrollstatus",
                "session", "campus", "location", "autoenroll", "classtopic", "classnotes", "consent", "gradingbasis",
                "instructionmode", "units", "classurl", "instructoruid", "examstatus", "examstarttime",
                "examendtime", "examlocation", "asstring", "objectclass", "course", "term"],
}

for key in COLUMNS.keys():
    data = pd.read_csv('data/raw/{}.csv'.format(key), usecols=COLUMNS[key])
    for k in DEFAULTS[key]:
        data[k].fillna('N/A', inplace=True)
    data.to_csv('data/{}.csv'.format(key), index=False)
