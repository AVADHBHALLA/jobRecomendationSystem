package org.example.controller;

import lombok.AllArgsConstructor;
import org.antlr.v4.runtime.misc.NotNull;
import org.example.entity.Job;
import org.example.models.JobDto;
import org.example.service.JobService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("api/v1/job")
@AllArgsConstructor
public class JobController {

    private final JobService jobService;

    @PostMapping("/create")
    public ResponseEntity<Job> create(@RequestBody JobDto dto){
        if(dto==null){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        try {
            Job job = jobService.create(dto);
            return new ResponseEntity<>(job,HttpStatus.CREATED);
        } catch (Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @GetMapping("/getAll")
    public ResponseEntity<List<Job>> getAll(){
        try{
            List<Job> jobList = jobService.getAll();
            return new ResponseEntity<>(jobList,HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @GetMapping("/get/{jobId}")
    public ResponseEntity<Job> get(@PathVariable UUID jobId){
        if(jobId == null){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        try{
            Job job = jobService.get(jobId);
            return new ResponseEntity<>(job,HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

   /* @PutMapping("/update/{jobId}")
    public ResponseEntity<Void> update(@PathVariable UUID jobId , @RequestBody JobDto dto){
        if(jobId==null && dto==null){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        try{

        }catch (Exception e){

        }
    }

    @DeleteMapping("/delete/{jobId}")
    public ResponseEntity<Void> delete(@PathVariable UUID jobId){

    }*/
}
