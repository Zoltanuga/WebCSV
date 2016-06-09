package by.persons.navigation;


import by.persons.navigation.impl.*;

public enum CommandEnum {
    MAIN_LIST_PERSONS {
        @Override
        public Command createCommand() {
            return new MainListPersonsCommand();
        }
    },
    MAIN_UPLOAD {
        @Override
        public Command createCommand() {
            return new MainUploadCommand();
        }
    },
    UPLOAD_FILE {
        @Override
        public Command createCommand() {
            return new UploadCommand();
        }
    },
    UPDATE_FILE {
        @Override
        public Command createCommand() {
            return new UpdateCommand();
        }
    },
    LIST_PERSONS {
        @Override
        public Command createCommand() {
            return new ListPersonsCommand();
        }
    },
    SORT_PERSONS {
        @Override
        public Command createCommand() {
            return new SortPersonsCommand();
        }
    },
    ERROR {
        @Override
        public Command createCommand() {
            return new ErrorCommand();
        }
    },
    SPLIT_PAGE {
        @Override
        public Command createCommand() {
            return new SplitPageCommand();
        }
    };

    public abstract Command createCommand();
}
