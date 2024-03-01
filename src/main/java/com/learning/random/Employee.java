package com.learning.random;

import lombok.*;

import java.util.Objects;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Employee implements Comparable<Employee>{
    private String firstName;
    private String lastName;
    private long employeeId;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || this.getClass() != o.getClass()) return false;
        Employee employee = (Employee) o;
        return employeeId == employee.employeeId && Objects.equals(firstName, employee.firstName) && Objects.equals(lastName, employee.lastName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(firstName, lastName, employeeId);
    }

    @Override
    public int compareTo(Employee obj) {
        String objFirstName = obj.firstName;
        String objLastName = obj.lastName;
        if(this.firstName.compareTo(objFirstName) == 0) {
            return this.lastName.compareTo(objLastName);
        }
        return this.firstName.compareTo(objFirstName);
    }
}
