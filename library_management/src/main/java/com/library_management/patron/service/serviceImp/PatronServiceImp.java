package com.library_management.patron.service.serviceImp;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.library_management.applicationData.entities.ApplicationPropertiesConstants;
import com.library_management.applicationData.entities.BaseEntity;
import com.library_management.applicationData.entities.Position;
import com.library_management.applicationData.repository.BaseEntityRepository;
import com.library_management.applicationData.repository.PositionRepository;
import com.library_management.patron.entity.Patron;
import com.library_management.patron.models.request.PatronRequestModel;
import com.library_management.patron.models.response.PatronResModel;
import com.library_management.patron.repository.PatronRepository;
import com.library_management.patron.service.PatronService;
import com.library_management.user.entity.User;
import com.library_management.user.repository.UserRepository;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
@Service
public class PatronServiceImp implements PatronService{
	@Autowired
	UserRepository userRepository;
	
	@Autowired
	PatronRepository patronRepository;
	
	@Autowired
	PositionRepository positionRepository;
	
	@Autowired
	BaseEntityRepository baseEntityRepository;
	
	@Override
	@Transactional
	public PatronResModel createpatron(@Valid PatronRequestModel patronRequestModel) throws JsonProcessingException {
		PatronResModel result =new PatronResModel();
		Patron patron = new Patron();
		User user = new User();
		BaseEntity baseEntity = new BaseEntity();
		
		user.setFirstName(patronRequestModel.getFirstName());
		user.setSecondName(patronRequestModel.getSecondName());
		user.setThirdName(patronRequestModel.getThirdName());
		user.setEmail(patronRequestModel.getEmail());
		user.setUserName(patronRequestModel.getUserName());
		user.setPassword(patronRequestModel.getPassword());
		user.setGender(patronRequestModel.getGender());
		user.setCity(patronRequestModel.getCity());
		user.setCountry(patronRequestModel.getCountry());
		user.setStreet(patronRequestModel.getStreet());
		user.setNationalityId(patronRequestModel.getNationalityId());
		user.setPhone(patronRequestModel.getPhone());
		Position position = positionRepository.findById(patronRequestModel.getPositionId()).get();
		user.setPosition(position);
		
		baseEntity.setCreaterId(ApplicationPropertiesConstants.Library_Management_user);
		baseEntity.setCreationDate(LocalDateTime.now());
		baseEntityRepository.save(baseEntity);
		user.setBaseEntity(baseEntity);
		userRepository.save(user);
		patron.setUser(user);
		patron.setBaseEntity(baseEntity);
		patronRepository.save(patron);
	
		
		result.setSuccess(true);
		result.setMessage("patron added succesfully");
		return result;
	}

	@Override
	@Transactional
	public List<PatronRequestModel> getAllPatrons() throws JsonProcessingException {
		  List<Patron> patrons = patronRepository.findAll();
		   List<PatronRequestModel> patronResModels = new ArrayList<>();
		   
		   for (Patron patron : patrons) {
			   PatronRequestModel resModel = new PatronRequestModel();
		        resModel.setUserName(patron.getUser().getUserName());
		        resModel.setEmail(patron.getUser().getEmail());
		        resModel.setFirstName(patron.getUser().getFirstName());
		        resModel.setSecondName(patron.getUser().getSecondName());
		        resModel.setThirdName(patron.getUser().getThirdName());
		        resModel.setGender(patron.getUser().getGender());
		        resModel.setCity(patron.getUser().getCity());
		        resModel.setCountry(patron.getUser().getCountry());
		        resModel.setStreet(patron.getUser().getStreet());
		        resModel.setPhone(patron.getUser().getPhone());
		        resModel.setNationalityId(patron.getUser().getNationalityId());
		        resModel.setPositionId(patron.getUser().getPosition().getId());
		        
		        patronResModels.add(resModel);
		    }
		return patronResModels;
	}

	@Override
	@Transactional
	public PatronRequestModel getPatronById(int id)  {
	    Optional<Patron> patronOpt = patronRepository.findById(id);
	    if (patronOpt.isPresent()) {
	        Patron patron = patronOpt.get();
	        PatronRequestModel resModel = new PatronRequestModel();
	        
	        resModel.setUserName(patron.getUser().getUserName());
	        resModel.setEmail(patron.getUser().getEmail());
	        resModel.setFirstName(patron.getUser().getFirstName());
	        resModel.setSecondName(patron.getUser().getSecondName());
	        resModel.setThirdName(patron.getUser().getThirdName());
	        resModel.setGender(patron.getUser().getGender());
	        resModel.setCity(patron.getUser().getCity());
	        resModel.setCountry(patron.getUser().getCountry());
	        resModel.setStreet(patron.getUser().getStreet());
	        resModel.setPhone(patron.getUser().getPhone());
	        resModel.setNationalityId(patron.getUser().getNationalityId());
	        resModel.setPositionId(patron.getUser().getPosition().getId());
	        
	        return resModel;
	    } else {
	        return null; // Patron not found
	    }
	}

	@Override
	@Transactional
	public PatronResModel updatePatron(int id, @Valid PatronRequestModel patronRequestModel) {
	    Optional<Patron> patronOpt = patronRepository.findById(id);
	    
	    if (patronOpt.isPresent()) {
	        Patron patron = patronOpt.get();
	        User user = patron.getUser();
	        
	        // Update user details
	        user.setFirstName(patronRequestModel.getFirstName());
	        user.setSecondName(patronRequestModel.getSecondName());
	        user.setThirdName(patronRequestModel.getThirdName());
	        user.setEmail(patronRequestModel.getEmail());
	        user.setUserName(patronRequestModel.getUserName());
	        user.setPassword(patronRequestModel.getPassword());
	        user.setGender(patronRequestModel.getGender());
	        user.setCity(patronRequestModel.getCity());
	        user.setCountry(patronRequestModel.getCountry());
	        user.setStreet(patronRequestModel.getStreet());
	        user.setNationalityId(patronRequestModel.getNationalityId());
	        user.setPhone(patronRequestModel.getPhone());
	        user.getBaseEntity().setChangeDate(LocalDateTime.now());
            user.getBaseEntity().setChangerId(ApplicationPropertiesConstants.Library_Management_Update_User);
	        // Update position
	        Position position = positionRepository.findById(patronRequestModel.getPositionId()).orElse(null);
	        if (position != null) {
	            user.setPosition(position);
	        }
	        
	        patron.getBaseEntity().setChangeDate(LocalDateTime.now());
	        patron.getBaseEntity().setChangerId(ApplicationPropertiesConstants.Library_Management_Update_User);
	        
	        // Save updated entities
	        userRepository.save(user);
	        patronRepository.save(patron);
	        
	        // Prepare response model
	        PatronResModel result = new PatronResModel();
	        result.setSuccess(true);
	        result.setMessage("Patron updated successfully");
	        return result;
	    } else {
	        return null; // Patron not found
	    }
	}

	@Override
	@Transactional
	public PatronResModel deletePatron(int id) {
	    Optional<Patron> patronOpt = patronRepository.findById(id);
	    
	    if (patronOpt.isPresent()) {
	        Patron patron = patronOpt.get();
	        
	     
	        // Delete patron
	        patronRepository.delete(patron);
	        
	        
	        // Delete associated user
	        User user = patron.getUser();
	        if (user != null) {
	            userRepository.delete(user);
	        }
	        
	           
	        // Delete associated baseEntity
	        BaseEntity baseEntity = patron.getBaseEntity();
	        if (baseEntity != null) {
	            baseEntityRepository.delete(baseEntity);
	        }
	        // Prepare response model
	        PatronResModel result = new PatronResModel();
	        result.setSuccess(true);
	        result.setMessage("Patron deleted successfully");
	        return result;
	    } else {
	        PatronResModel result = new PatronResModel();
	        result.setSuccess(false);
	        result.setMessage("Patron not found");
	        return result;
	    }
	}



	
	
	


}
