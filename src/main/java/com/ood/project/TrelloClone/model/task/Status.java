package com.ood.project.TrelloClone.model.task;

public enum Status {
    TODO {
        public Status undo() {
            return TODO;
        }

        public Status transition() {
            return DOING;
        }
    },
    DOING {
        public Status undo() {
            return TODO;
        }

        public Status transition() {
            return DONE;
        }
    },
    DONE {
        public Status undo() {
            return DOING;
        }

        public Status transition() {
            return TODO;
        }
    };

    public abstract Status undo();

    public abstract Status transition();
}
