package com.Booking.Booking.Controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.Booking.Booking.Entities.BookingData;
import com.Booking.Booking.Exception.EntityNotFoundException;
import com.Booking.Booking.Model.BookingDeleteResponse;
import com.Booking.Booking.Model.BookingPostRequest;
import com.Booking.Booking.Model.BookingPostResponse;
import com.Booking.Booking.Model.BookingPutRequest;
import com.Booking.Booking.Model.BookingPutResponse;
import com.Booking.Booking.Service.BookingService;

import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
public class BookingController {

	@Autowired
	private BookingService bookingService;

	@PostMapping("/booking")
	public ResponseEntity<BookingPostResponse> addBooking(@Valid @RequestBody BookingPostRequest request) {
		log.info("Post Controller Started");
		return new ResponseEntity<>(bookingService.addBooking(request), HttpStatus.CREATED);
	}

	@PutMapping("/booking/{bookingId}")
	public ResponseEntity<BookingPutResponse> updateBooking(@Valid @RequestBody BookingPutRequest request,
			@PathVariable String bookingId) throws EntityNotFoundException {
		log.info("Put Controller Started");

		return new ResponseEntity<>(bookingService.updateBooking(bookingId, request), HttpStatus.OK);
	}

	@GetMapping("/booking/{bookingId}")
	public ResponseEntity<BookingData> getDataById(@PathVariable String bookingId) throws EntityNotFoundException {
		log.info("Get Controller Started");
		return new ResponseEntity<>(bookingService.getDataById(bookingId), HttpStatus.OK);
	}

	@GetMapping("/booking")
	public ResponseEntity<List<BookingData>> getData(@RequestParam(value = "pageNo", required = false) Integer pageNo,
			@RequestParam(value = "cancel", required = false) Boolean cancel,
			@RequestParam(value = "completed", required = false) Boolean completed,
			@RequestParam(value = "transporterId", required = false) String transporterId,
			@RequestParam(value = "postLoadId", required = false) String postLoadId) throws EntityNotFoundException {
		log.info("Get with Params Controller Started");
		return new ResponseEntity<>(bookingService.getDataById(pageNo, cancel, completed, transporterId, postLoadId),
				HttpStatus.OK);
	}

	@DeleteMapping("/booking/{bookingId}")
	public ResponseEntity<BookingDeleteResponse> deleteBooking(@PathVariable String bookingId)
			throws EntityNotFoundException {
		log.info("Delete Controller Started");
		return new ResponseEntity<>(bookingService.deleteBooking(bookingId), HttpStatus.OK);
	}
}
