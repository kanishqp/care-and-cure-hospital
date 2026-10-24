package com.cac.controller;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.cac.entity.Appointment;
import com.cac.entity.Doctor;
import com.cac.service.DoctorService;


@RestController
public class DoctorController {
    
	@Autowired
	DoctorService doctorService;
	
	@GetMapping("/")
	public String layout() {
		return doctorService.layout();
	}

	@GetMapping("/home")
	public String home() {
		return doctorService.home();
	}

	@PostMapping("/addDoctor")
	public ResponseEntity<Doctor> addDoctor(@RequestBody Doctor doctor) {
		Doctor doctorObj = doctorService.addDoctor(doctor);
		return new ResponseEntity<>(doctorObj, HttpStatus.CREATED);
	}
	
	@PutMapping("/updateDoctor/{doctorId}")
	public ResponseEntity<Doctor> updateDoctor(@PathVariable int doctorId, @RequestBody Doctor doctor) {
		doctor.setDoctorId(doctorId);
		Doctor doctorObj = doctorService.updateDoctor(doctor);
		return new ResponseEntity<>(doctorObj, HttpStatus.OK);
	}
	
	@GetMapping("/viewDoctorById/{doctorId}")
	public ResponseEntity<Doctor> viewDoctorById(@PathVariable int doctorId) {
		Doctor doctor = doctorService.viewDoctorById(doctorId);
		return new ResponseEntity<>(doctor, HttpStatus.OK);
	}

	@GetMapping("/viewAllDoctors")
	public ResponseEntity<List<Doctor>> viewAllDoctors() {
		List<Doctor> doctors = doctorService.viewAllDoctors();
		return new ResponseEntity<>(doctors, HttpStatus.OK);
	}
	
	@GetMapping("/viewDoctorBySpecialization/{specialization}")
    public ResponseEntity<List<Doctor>> viewDoctorsBySpecialization(@PathVariable String specialization) {
		List<Doctor> doctors = doctorService.viewDoctorsBySpecialization(specialization);
		return new ResponseEntity<>(doctors, HttpStatus.OK);
    }
	
	@GetMapping("/viewAllActiveDoctors")
	public ResponseEntity<List<Doctor>> viewAllActiveDoctors() {
		List<Doctor> doctors = doctorService.viewAllActiveDoctors();
		return new ResponseEntity<>(doctors, HttpStatus.OK);
	}
	
	@GetMapping("/viewDoctorAvailabilityBySlot/{slot}")
    public ResponseEntity<List<Doctor>> viewDoctorAvailabilityBySlot(@PathVariable LocalDateTime slot) {
        List<Doctor> doctors = doctorService.viewDoctorAvailabilityBySlot(slot);
        return new ResponseEntity<>(doctors, HttpStatus.OK);
    }

    @GetMapping("/viewDoctorAvailabilityByDay/{day}")
    public ResponseEntity<List<Doctor>> viewDoctorAvailabilityByDay(@PathVariable LocalDate day) {
        List<Doctor> doctors = doctorService.viewDoctorAvailabilityByDay(day);
        return new ResponseEntity<>(doctors, HttpStatus.OK);
    }
    
    @PutMapping("/deactivate/{doctorId}")
    public ResponseEntity<String> deactivateDoctor(@PathVariable int doctorId) {
        boolean isDeactivated = doctorService.deactivateDoctor(doctorId);
        if (isDeactivated) {
            return ResponseEntity.ok("Doctor deactivated successfully with doctor Id: "+doctorId);
        } else {
            return ResponseEntity.badRequest().body("Doctor not found or already deactivated.");
        }
    }

    @GetMapping("/viewDoctorsByConsultationCount")
    public ResponseEntity<List<Doctor>> viewDoctorsByConsultationCount(
            @RequestParam LocalDate startDate,
            @RequestParam LocalDate endDate,
            @RequestParam int minCount) {
        List<Doctor> doctors = doctorService.viewDoctorsByConsultationCount(startDate, endDate, minCount);
        return new ResponseEntity<>(doctors, HttpStatus.OK);
    }
    
    @GetMapping("/getDoctorAppointmentReport/{doctorId}")
    public ResponseEntity<List<Appointment>> getDoctorAppointmentReport(
            @PathVariable int doctorId,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate endDate) {
        List<Appointment> report = doctorService.generateDoctorAppointmentReport(doctorId, startDate, endDate);
        return new ResponseEntity<>(report, HttpStatus.OK);
    }

    @GetMapping("/getDoctorRevenueReport/{doctorId}")
    public ResponseEntity<Double> getDoctorRevenueReport(
            @PathVariable int doctorId,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate endDate) {
        Double revenue = doctorService.generateDoctorRevenueReport(doctorId, startDate, endDate);
        return new ResponseEntity<>(revenue, HttpStatus.OK);
    }

    @GetMapping("/getLowConsultationDoctorsReport")
    public ResponseEntity<List<Doctor>> getLowConsultationDoctorsReport(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate endDate) {
        List<Doctor> report = doctorService.generateLowConsultationDoctorsReport(startDate, endDate);
        return new ResponseEntity<>(report, HttpStatus.OK);
    }


    @GetMapping("/getMostFrequentlyBookedDoctorsReport")
    public ResponseEntity<List<Doctor>> getMostFrequentlyBookedDoctorsReport(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate endDate) {
        List<Doctor> report = doctorService.generateMostFrequentlyBookedDoctorsReport(startDate, endDate);
        return new ResponseEntity<>(report, HttpStatus.OK);
    }

    @GetMapping("/getDoctorAvailabilityReport")
    public ResponseEntity<List<Doctor>> getDoctorAvailabilityReport(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date) {
        List<Doctor> report = doctorService.generateDoctorAvailabilityReport(date);
        return new ResponseEntity<>(report, HttpStatus.OK);
    }
}
