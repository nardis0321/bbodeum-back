package com.bbodeum.course.control;

import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.bbodeum.file.application.FileFacade;
import com.bbodeum.file.domain.FileCommand;
import com.bbodeum.file.domain.FileInfo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import com.bbodeum.course.dto.CourseDTOLight;
import com.bbodeum.course.dto.CourseDTORequest;
import com.bbodeum.course.dto.CourseInfoDTO;
import com.bbodeum.course.service.CourseService;
import com.bbodeum.dto.PageBean;
import com.bbodeum.exception.AddException;
import com.bbodeum.exception.FindException;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("courses/*")
public class CourseController {
	private final CourseService courseService;
	private final FileFacade fileFacade;
	
	@GetMapping(value="classes", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> courseList(int page, HttpServletResponse response) throws FindException{ //, FileNotFoundException {
		PageBean<CourseDTOLight> bean = courseService.getCourseAll(page);
		return new ResponseEntity<>(bean, HttpStatus.OK); 
	}
	
	@GetMapping(value="classes/{courseId}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> courseDetail(@PathVariable("courseId") Long courseId) throws FindException {
		CourseDTOLight dto = courseService.getCourseById(courseId);
		return new ResponseEntity<>(dto, HttpStatus.OK);
	}
	
	@GetMapping(value = "classes/{courseId}/img", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> courseDetailImg(@PathVariable("courseId") Long courseId) throws FindException {

		Optional<byte[]> bytes = fileFacade.retrieveCourseImage(courseId);
		if (bytes.isEmpty()) return ResponseEntity.notFound().build();

		byte[] bArr = bytes.get();
		FileInfo fileInfo = fileFacade.retrieveFileInfo(courseId);
		return ResponseEntity.ok()
				.contentType(MediaType.parseMediaType(fileInfo.getContentType()))
				.contentLength(bArr.length)
				.body(bArr);
	}
	
	@GetMapping(value="info", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> courseInfoList() throws FindException {
		List<CourseInfoDTO> list = courseService.getAllCourseInfo();
		return new ResponseEntity<>(list, HttpStatus.OK); 
	}
	
	@PostMapping(value = "classes",
			consumes = MediaType.MULTIPART_FORM_DATA_VALUE,
			produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> write(HttpSession session,
								   @ModelAttribute CourseDTORequest dto,
								   @RequestPart("f") MultipartFile file) throws AddException {

		String logined = (String)session.getAttribute("logined");
		if(logined == null) {
			return new ResponseEntity<>("로그인이 안된 상태입니다", HttpStatus.INTERNAL_SERVER_ERROR);
		}

		dto.setTrId(logined);
		Long courseId = courseService.addCourse(dto);
		dto.setCourseId(courseId);
		System.out.println("debug "+dto);
		FileCommand fileCommand = FileCommand.from(dto, file);
		System.out.println("fileCommand = " + fileCommand);
		String fileToken = fileFacade.registerFile(fileCommand);
		System.out.println("fileToken = " + fileToken);
//		String fileToken = fileFacade.registerFile(FileCommand.from(dto, file));
		return ResponseEntity.ok(fileToken);
	}
}
