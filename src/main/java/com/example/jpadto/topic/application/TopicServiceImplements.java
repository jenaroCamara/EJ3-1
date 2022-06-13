package com.example.jpadto.topic.application;

import com.example.jpadto.application.UsuarioServicioInterface;
import com.example.jpadto.persona.infraestructure.dto.output.OutputDTOPersona;
import com.example.jpadto.persona.infraestructure.dto.output.outputDTOpersonafull;
import com.example.jpadto.topic.application.port.topicService;
import com.example.jpadto.topic.domain.Topic;
import com.example.jpadto.topic.infraestructure.dto.input.inputDTOtopic;
import com.example.jpadto.topic.infraestructure.dto.output.outputDTOtopic;
import com.example.jpadto.topic.infraestructure.repository.TopicRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

@Service
public class TopicServiceImplements implements topicService {
    //sus autowired
    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private TopicRepository topicRepository;
    @Autowired
    private UsuarioServicioInterface usuarioServicio;
    //sus funciones
    public outputDTOtopic crearTopic(inputDTOtopic dtotopic) throws Exception{
        //acordarse de cuando implementar technology, añadirse con un ser a la entidad antes de guardarlo en el repository
        return modelMapper.map(topicRepository.save(modelMapper.map(dtotopic, Topic.class)), outputDTOtopic.class);
    }


    /*
    public outputDTOscore anadirScore(@RequestBody inputDTOscore dtOscore) throws Exception;
    public outputDTOscore getElementbyId(@PathVariable String id) throws Exception;
    public outputDTOscore actualiza(@RequestBody inputDTOscore SCORE) throws Exception;
    public void deleteById(@PathVariable String id) throws Exception;
     */
}
