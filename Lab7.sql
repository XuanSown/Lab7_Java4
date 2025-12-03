create database Lab7

use Lab7

create table Employees(
    Id nvarchar(10) primary key,
    Name nvarchar(100) not null,
    Gender bit not null, --1: Name, 0: Nữ
    Salary float not null
)

INSERT INTO Employees (id, name, gender, salary) VALUES
('NV01', N'Nhân viên 01', 1, 500.0),
('NV02', N'Nhân viên 02', 0, 1500.0),
('NV03', N'Nhân viên 03', 1, 5000.0),
('NV04', N'Nhân viên 04', 0, 2500.0),
('NV05', N'Nhân viên 05', 1, 3500.0);

select * from Employees
