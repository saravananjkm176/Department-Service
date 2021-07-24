package com.s3.department.service.main.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.s3.department.service.main.model.Department;
import com.s3.department.service.main.model.EmployProfile;

@RestController
@RequestMapping("/rest/dept")
public class DepartmentController {
    
    @Autowired
    private RestTemplate template;
    
    /**
     * 
     * @return Department[]
     */
    @GetMapping(value="/all", produces=MediaType.APPLICATION_JSON_VALUE)
    public Department[] getDepartments() {
        return getAllDepartment();
    }
    
    /**
     * 
     * @param name
     * @return Department
     */
    @GetMapping(value="/{name}", produces=MediaType.APPLICATION_JSON_VALUE)
    public Department getDepartmentByName(@PathVariable String name) {
        Department department = new Department();
        for (Department depart : getAllDepartment()) {
            if(depart.getName().equalsIgnoreCase(name)) {
                department = depart;
            }
        }
        return department;
    }
    
    /**
     * 
     * @param name
     * @return Department
     */
    @GetMapping(value="/{deptCode}", produces=MediaType.APPLICATION_JSON_VALUE)
    public Department getDepartmentBydeptCode(@PathVariable String deptCode) {
        Department department = new Department();
        for (Department depart : getAllDepartment()) {
            if(depart.getDeptCode().equalsIgnoreCase(deptCode)) {
                department = depart;
            }
        }
        System.out.println("calling ...... getEmployProfileByNameAndDeptcode()" + department);
        return department;
    }
    
    /**
     * 
     * @param name
     * @param deptCode
     * @throws JsonProcessingException 
     */
    @GetMapping(value="/profile/{empName}/{deptCode}", produces=MediaType.APPLICATION_JSON_VALUE)
    public EmployProfile getEmployProfileByNameAndDeptcode(@PathVariable String empName, @PathVariable String deptCode) throws JsonProcessingException {
        System.out.println("calling ...... getEmployProfileByNameAndDeptcode()");
        
        Department department = getDepartmentBydeptCode(deptCode);
        ObjectMapper mapper = new ObjectMapper();
        System.out.println("profile -department :: " + mapper.writeValueAsString(department) + "\n");
        //final String uri = "http://localhost:8020/rest/emp/"+ empName+ "/"+deptCode;
        final String uri = "http://EMPLOYEE-SERVICE/rest/emp/"+ empName+ "/"+deptCode;
        System.out.println("uri " + uri);
        //ResponseEntity<EmployProfile> responseEntity = template.exchange(uri, HttpMethod.GET, HttpEntity<EmployProfile>, EmployProfile.class);
        EmployProfile empProfile = template.getForObject(uri, EmployProfile.class);
        //String empProfile1 =template.getForObject(uri, String.class);
        empProfile.setName(department.getName());
        empProfile.setCity(department.getCity());
        empProfile.setVillage(department.getVillage());
        empProfile.setPincode(department.getPincode());
        empProfile.setLotalStaff(department.getLotalStaff());
        return empProfile;
    }
    
    /**
     * 
     * @return Department[]
     */
    private Department[] getAllDepartment() {
        Department[] department = new Department[4];
        department[0]= setDepartmentInfo("ITDE-001", "Software", "Chennai","Nanthanam", 600021, 1500 );
        department[1]= setDepartmentInfo("ITQA-001", "Quality Control", "Bangalor","Mangalor", 400021 ,3350);
        department[2]= setDepartmentInfo("GOADM001", "Government","Trichy", "Mannarpuram", 650421, 1690);
        department[3]= setDepartmentInfo("GOPOL001", "Government","Dharmapuri","Malaiyanur",680221, 310 ); 
        return department;
    }
    
    /**
     * 
     * @param deptCode
     * @param name
     * @param city
     * @param village
     * @param pincode
     * @param lotalStaff
     * @return Department
     */
    private Department setDepartmentInfo(String deptCode, String name, String city, String village, long pincode, long lotalStaff) {
        Department depart = new Department(); 
        depart.setDeptCode(deptCode);
        depart.setName(name);
        depart.setCity(city);
        depart.setVillage(village);
        depart.setPincode(pincode);
        depart.setLotalStaff(lotalStaff);
        return depart;
    }
}
