package sg.edu.nus.iss.lecture15day.services;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.xml.transform.Templates;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.amqp.RabbitProperties;
import org.springframework.stereotype.Service;

import sg.edu.nus.iss.lecture15day.model.Person;
import sg.edu.nus.iss.lecture15day.repo.ListRepo;

@Service
public class PersonService {
 
  
  @Autowired
  ListRepo listRepo;



  public void addPerson (String redisKey, Person person){
    String id = UUID.randomUUID().toString().toUpperCase().substring(0, 4);
    String personData = id + "," + person.getFullName() + "," + person.getEmail() + "," + person.getDateofBirth();
    listRepo.rightPush(redisKey, personData);
  }

  public List<Person> findAll (String redisKey){
    List<String> listData =  listRepo.getList(redisKey);

    List<Person> persons = new ArrayList<>();

    for(String data : listData){
      String[] rawData = data.split(",");
      String id = rawData[0];  // The first element is the ID
      String fullName = rawData[1];  // The second element is the full name
      String email = rawData[2];  // The third element is the email
      String rawDob = rawData[3];  // The fourth element is the date of birth
      
      // Clean and parse the date of birth
      String cleanedDob = rawDob.replaceAll("[^\\d-]", "").trim();
      LocalDate dob = LocalDate.parse(cleanedDob);
      
      // Create a Person object with the parsed data
      Person p = new Person(id, fullName, email, dob);
      persons.add(p);

    }
    return persons;
  }

//   public List<Person> findAll(String redisKey) {
//     List<String> listData = listRepo.getList(redisKey);  // Fetch data from Redis
//     List<Person> persons = new ArrayList<>();

//     for (String data : listData) {
//         // Split the data into parts based on commas
//         String[] rawData = data.split(",");

//         // Check if the data is valid (at least 4 elements: id, fullname, email, dob)
//         if (rawData.length == 4) {
//             try {
//                 String id = rawData[0].trim();  // The first element is the ID
//                 String fullName = rawData[1].trim();  // The second element is the full name
//                 String email = rawData[2].trim();  // The third element is the email
//                 String rawDob = rawData[3].trim();  // The fourth element is the date of birth

//                 // Clean and parse the date of birth, assuming it needs to be in "yyyy-MM-dd" format
//                 LocalDate dob = null;
//                 try {
//                     dob = LocalDate.parse(rawDob);  // Try parsing the DOB
//                 } catch (DateTimeParseException e) {
//                     // Handle invalid date format, log and skip
//                     System.err.println("Invalid date format for person: " + fullName + ", DOB: " + rawDob);
//                     continue;  // Skip this entry and proceed with the next one
//                 }

//                 // Create a Person object with the parsed data
//                 Person p = new Person(id, fullName, email, dob);
//                 persons.add(p);

//             } catch (Exception e) {
//                 // Log and skip if any other error occurs during data parsing
//                 System.err.println("Error parsing person data: " + e.getMessage() + " for data: " + data);
//             }
//         } else {
//             // Log invalid data format and skip the entry if it doesn't have 4 elements
//             System.err.println("Invalid data format for person (not enough fields): " + data);
//         }
//     }

//     return persons;  // Return the list of valid Person objects
// }


  public void removebyvalue(String redisKey, Integer count, Person person){
      // Convert the Person object to its stored string format
      String personData = person.getId() + "," + person.getFullName() + "," 
                          + person.getEmail() + "," + person.getDateofBirth();
  
      // Remove the person data from the Redis list
      listRepo.remove(redisKey, count, personData);
  }
  
  public Boolean delete(String redisKey){
    return listRepo.delete(redisKey);
  }
}
