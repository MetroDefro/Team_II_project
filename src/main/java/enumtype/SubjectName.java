package enumtype;

import java.util.InputMismatchException;

public enum SubjectName {
    Java("Java") {
        @Override
        public SubjectType getSubjectType() {
            return SubjectType.ESSENTIAL;
        }
    },
    OOP("객체지향") {
        @Override
        public SubjectType getSubjectType() {
            return SubjectType.ESSENTIAL;
        }
    },
    Spring("Spring") {
        @Override
        public SubjectType getSubjectType() {
            return SubjectType.ESSENTIAL;
        }
    },
    JPA("JPA") {
        @Override
        public SubjectType getSubjectType() {
            return SubjectType.ESSENTIAL;
        }
    },
    MySQL("MySQL") {
        @Override
        public SubjectType getSubjectType() {
            return SubjectType.ESSENTIAL;
        }
    },
    DesignPattern("디자인 패턴") {
        @Override
        public SubjectType getSubjectType() {
            return SubjectType.SELECT;
        }
    },
    SpringSecurity("Spring Security") {
        @Override
        public SubjectType getSubjectType() {
            return SubjectType.SELECT;
        }
    },
    Redis("Redis") {
        @Override
        public SubjectType getSubjectType() {
            return SubjectType.SELECT;
        }
    },
    MongoDB("MongoDB") {
        @Override
        public SubjectType getSubjectType() {
            return SubjectType.SELECT;
        }
    };

    private final String name;

    SubjectName(String name) {
        this.name = name;
    }

    public abstract SubjectType getSubjectType();

    public String getName() {
        return name;
    }

    public int getId() {
        return ordinal() + 1;
    }

    public static SubjectName getSubjectName(int id) {
        for (SubjectName type : SubjectName.values()) {
            if(type.getId() == id) {
                return type;
            }
        }
        throw new IllegalArgumentException("해당하는 과목이 없습니다. ID: " + id); // 일치하는 게 없으면 예외처리
    }

    public static SubjectName getSubjectName(String input) {
        for (SubjectName type : SubjectName.values()) {
            if(type.name.equals(input)) {
                return type;
            }
        }
        throw new IllegalArgumentException("해당하는 과목이 없습니다. 이름: " + input); // 일치하는 게 없으면 예외처리
    }
}
