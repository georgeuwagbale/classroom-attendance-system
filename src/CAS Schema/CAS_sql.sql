/* 
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Other/SQLTemplate.sql to edit this template
 */
/**
 * Author:  uwagb
 * Created: Feb 6, 2023
 */

create table if not exists CAS.School
(
    SchoolID int auto_increment primary key,
    Name     varchar(200) null
);

create table if not exists CAS.Department
(
    DepartmentID int auto_increment primary key,
    Name         varchar(200) null,
    SchoolID     int not null,
    constraint Department_ibfk_1
        foreign key (SchoolID) references CAS.School (SchoolID)
            on delete cascade
);

create table if not exists CAS.Course
(
    CourseID     int auto_increment
        primary key,
    DepartmentID int          null,
    Name         varchar(100) not null,
    Code         varchar(6)   null,
    Unit         int          not null,
    constraint Code
        unique (Code),
    constraint Course_ibfk_1
        foreign key (DepartmentID) references CAS.Department (DepartmentID)
            on delete cascade
);

create table if not exists CAS.Attendance
(
    AttendanceID int auto_increment
        primary key,
    CourseID     int      null,
    Date         date     not null,
    StartTime    time     not null,
    StopTime     time     null,
    Status       smallint not null,
    constraint Attendance_ibfk_1
        foreign key (CourseID) references CAS.Course (CourseID)
            on delete cascade
);

create table if not exists CAS.User
(
    UserID      int auto_increment
        primary key,
    FirstName   varchar(50)  not null,
    LastName    varchar(50)  not null,
    MiddleName  varchar(50)  null,
    Gender      char         not null,
    DateOfBirth date         not null,
    PhoneNumber varchar(30)  null,
    Image       longblob     not null,
    Email       varchar(300) not null,
    Password    varchar(500) not null,
    FingerPrint blob         not null,
    constraint Email
        unique (Email)
);

create table if not exists CAS.Address
(
    AddressID  int auto_increment
        primary key,
    UserID     int          null,
    StreetNO   int          null,
    StreetName varchar(500) not null,
    LocalGovt  varchar(500) not null,
    State      varchar(300) not null,
    constraint Address_ibfk_1
        foreign key (UserID) references CAS.User (UserID)
            on delete cascade
);

create table if not exists CAS.Faculty
(
    FacultyID    int auto_increment
        primary key,
    UserID       int not null,
    EmployeeID   int not null,
    DepartmentID int not null,
    constraint EmployeeID
        unique (EmployeeID),
    constraint Faculty_ibfk_1
        foreign key (UserID) references CAS.User (UserID)
            on delete cascade,
    constraint Faculty_ibfk_2
        foreign key (DepartmentID) references CAS.Department (DepartmentID)
            on delete cascade
);

create table if not exists CAS.Dean
(
    DeanID    int auto_increment
        primary key,
    FacultyID int not null,
    constraint DeanID
        unique (DeanID),
    constraint FacultyID
        unique (FacultyID),
    constraint Dean_ibfk_1
        foreign key (FacultyID) references CAS.Faculty (FacultyID)
);

create table if not exists CAS.HOD
(
    HODID     int auto_increment
        primary key,
    FacultyID int not null,
    constraint HOD_ibfk_1
        foreign key (FacultyID) references CAS.Faculty (FacultyID)
            on delete cascade
);

create table if not exists CAS.Lecturer
(
    LecturerID int auto_increment
        primary key,
    FacultyID  int null,
    constraint Lecturer_ibfk_1
        foreign key (FacultyID) references CAS.Faculty (FacultyID)
            on delete cascade
);

create table if not exists CAS.LecturerCourse
(
    LecturerCourseID int auto_increment
        primary key,
    LecturerID       int null,
    CourseID         int null,
    constraint LecturerCourse_Course_CourseID_fk
        foreign key (CourseID) references CAS.Course (CourseID)
            on delete cascade,
    constraint LecturerCourse_ibfk_1
        foreign key (LecturerID) references CAS.Lecturer (LecturerID)
            on delete cascade
);

create table if not exists CAS.ProgramCoordinator
(
    ProgramCoordinatorID int auto_increment
        primary key,
    FacultyID            int not null,
    constraint ProgramCoordinator_ibfk_1
        foreign key (FacultyID) references CAS.Faculty (FacultyID)
            on delete cascade
);

create table if not exists CAS.Student
(
    StudentID    int auto_increment
        primary key,
    UserID       int         not null,
    MatricNo     varchar(11) not null,
    DepartmentID int         not null,
    Level        varchar(4)  not null,
    constraint MatricNo
        unique (MatricNo),
    constraint Student_ibfk_1
        foreign key (UserID) references CAS.User (UserID)
            on delete cascade,
    constraint Student_ibfk_2
        foreign key (DepartmentID) references CAS.Department (DepartmentID)
            on delete cascade
);

create table if not exists CAS.StudentAttendance
(
    StudentAttendanceID int auto_increment
        primary key,
    StudentID           int  null,
    AttendanceID        int  null,
    Status              char null,
    constraint StudentAttendance_ibfk_1
        foreign key (StudentID) references CAS.Student (StudentID)
            on delete cascade,
    constraint StudentAttendance_ibfk_2
        foreign key (AttendanceID) references CAS.Attendance (AttendanceID)
            on delete cascade
);

create table if not exists CAS.StudentCourse
(
    StudentCourseID int auto_increment
        primary key,
    StudentID       int null,
    CourseID        int null,
    constraint StudentCourse_ibfk_1
        foreign key (StudentID) references CAS.Student (StudentID)
            on delete cascade,
    constraint StudentCourse_ibfk_2
        foreign key (CourseID) references CAS.Course (CourseID)
            on delete cascade
);

