package com.rasl.controller;
import com.sun.scenario.effect.impl.sw.sse.SSEBlend_SRC_OUTPeer;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.web.JsonPath;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;


@Controller
@RequestMapping("/stopwatch")
public class StopwatchController {

    static List<Stopwatch> stopwatchList = new ArrayList<>();
    static int stopwatchCounter = 0;

    @RequestMapping(method = RequestMethod.GET)
    public String stopwatch(Model model){
        return "stopwatch";
    }

    @RequestMapping("/api/add")
    public @ResponseBody
    ResponseEntity<List<Stopwatch>> add () {
         stopwatchList.add(new Stopwatch(++stopwatchCounter));
         return new ResponseEntity<List<Stopwatch>>(stopwatchList, HttpStatus.OK);
    }

    @RequestMapping(value = "/api/remove", produces = "application/json", method = RequestMethod.POST)
    public @ResponseBody
    ResponseEntity<List<Stopwatch>> remove () {

        if (stopwatchCounter > 0) {
            stopwatchCounter--;
        }

        if (stopwatchList.size() > 0) {
            stopwatchList.remove(stopwatchList.size() - 1);
        }

        return new ResponseEntity<List<Stopwatch>>(stopwatchList, HttpStatus.OK);
    }

    @RequestMapping(value = "/api/start/{id}", produces = "application/json", method = RequestMethod.POST)
    public @ResponseBody
    ResponseEntity<List<Stopwatch>> start(@PathVariable Integer id, @RequestBody List<Stopwatch> stopwatchList){
        this.stopwatchList = stopwatchList;
        for (Stopwatch stopwatch : stopwatchList) {
            stopwatch.started = false;
        }
        stopwatchList.get(id -1).started = true;
        return new ResponseEntity<List<Stopwatch>>(stopwatchList, HttpStatus.OK) ;
    }

    @RequestMapping(value = "/api/stop/{id}", produces = "application/json", method = RequestMethod.POST)
    public @ResponseBody
    ResponseEntity stop(@PathVariable Integer id, @RequestBody List<Stopwatch> swGet){
        //Получить из списка по id и started=true и time=time
        stop(id-1, swGet.get(id-1).time );
        return new ResponseEntity<List<Stopwatch>>(stopwatchList,HttpStatus.OK);
    }

    @RequestMapping(value = "/api/test", method = RequestMethod.POST)
    public @ResponseBody
    ResponseEntity test(@RequestBody Stopwatch stopwatch){
        System.out.println(stopwatch.toString());
        return new ResponseEntity(HttpStatus.OK);
    }

    private void stop(int id, int time) {

        System.out.println("Stopping " + stopwatchList.get(id));

        stopwatchList.get(id).started = false;
        stopwatchList.get(id).time = time;
    }

    @Getter
    @Setter
    static class Stopwatch {
        int id;
        int time;
        boolean started = false;

        public Stopwatch(int id) {
            this.id = id;
        }

        public Stopwatch() {
        }

        @Override
        public String toString() {
            return "Stopwatch{" +
                    "id=" + id +
                    ", time=" + time +
                    ", started=" + started +
                    '}';
        }
    }
}
