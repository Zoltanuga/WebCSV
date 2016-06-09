package by.persons.database;

import java.util.ResourceBundle;

public enum SortMode {
    SORT_BY_NAME {
        @Override
        public String obtainMode(ResourceBundle queries) {
            return queries.getString("sqLSelectPersonsSortedByName");
        }
    },
    SORT_BY_SURNAME {
        @Override
        public String obtainMode(ResourceBundle queries) {
            return queries.getString("sqLSelectPersonsSortedBySurname");
        }
    },
    SORT_BY_EMAIL {
        @Override
        public String obtainMode(ResourceBundle queries) {
            return queries.getString("sqLSelectPersonsSortedByEmail");
        }
    },
    SORT_BY_PHONE_NUMBER {
        @Override
        public String obtainMode(ResourceBundle queries) {
            return queries.getString("sqLSelectPersonsSortedByPhoneNumber");
        }
    },
    SORT_BY_LOGIN {
        @Override
        public String obtainMode(ResourceBundle queries) {
            return queries.getString("sqLSelectPersonsSortedByLogin");
        }
    };

    public abstract String obtainMode(ResourceBundle queries);
}
