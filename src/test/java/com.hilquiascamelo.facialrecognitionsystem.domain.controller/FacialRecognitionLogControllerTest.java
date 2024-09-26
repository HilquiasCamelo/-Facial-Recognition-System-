package com.hilquiascamelo.facialrecognitionsystem.domain.controller;

import ch.qos.logback.core.joran.util.beans.BeanUtil;
import com.hilquiascamelo.facialrecognitionsystem.domain.controller.CustomUtils;
import com.hilquiascamelo.facialrecognitionsystem.domain.controller.FacialRecognitionLogController;
import com.hilquiascamelo.facialrecognitionsystem.domain.dto.FacialRecognitionLogDto;
import com.hilquiascamelo.facialrecognitionsystem.domain.mapper.FacialRecognitionLogMapper;
import com.hilquiascamelo.facialrecognitionsystem.domain.model.FacialRecognitionLog;
import com.hilquiascamelo.facialrecognitionsystem.domain.service.FacialRecognitionLogService;
import org.hamcrest.Matchers;
import org.hamcrest.core.Is;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.runner.RunWith;
import org.mockito.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.Collections;

@Transactional
public class FacialRecognitionLogControllerTest {
    private static final String ENDPOINT_URL = "/api/facial-recognition-log";

    @InjectMocks
    private FacialRecognitionLogController facialrecognitionlogController;

    @Mock
    private FacialRecognitionLogService facialrecognitionlogService;

    private MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders
                .standaloneSetup(facialrecognitionlogController)
                .build();
    }

    @Test
    public void findAllByPage() throws Exception {
        Page<FacialRecognitionLogDto> page = new PageImpl<>(Collections.singletonList(FacialRecognitionLogBuilder.getDto()));

        Mockito.when(facialrecognitionlogService.findByCondition(ArgumentMatchers.any(), ArgumentMatchers.any()))
                .thenReturn(page);

        ResultActions resultActions = mockMvc.perform(MockMvcRequestBuilders.get(ENDPOINT_URL)
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.data.content", Matchers.hasSize(1)));

        Mockito.verify(facialrecognitionlogService, Mockito.times(1)).findByCondition(ArgumentMatchers.any(), ArgumentMatchers.any());
        Mockito.verifyNoMoreInteractions(facialrecognitionlogService);
    }

    @Test
    public void getById() throws Exception {
        Mockito.when(facialrecognitionlogService.findById(ArgumentMatchers.anyLong()))
                .thenReturn(FacialRecognitionLogBuilder.getDto());

        mockMvc.perform(MockMvcRequestBuilders.get(ENDPOINT_URL + "/1"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content()
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.jsonPath("$.id", Is.is(1)));

        Mockito.verify(facialrecognitionlogService, Mockito.times(1)).findById("1");
        Mockito.verifyNoMoreInteractions(facialrecognitionlogService);
    }

    @Test
    public void save() throws Exception {
        Mockito.when(facialrecognitionlogService.save(ArgumentMatchers.any(FacialRecognitionLogDto.class)))
                .thenReturn(FacialRecognitionLogBuilder.getDto());

        mockMvc.perform(
                        MockMvcRequestBuilders.post(ENDPOINT_URL)
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(CustomUtils.asJsonString(FacialRecognitionLogBuilder.getDto())))
                .andExpect(MockMvcResultMatchers.status().isCreated());

        Mockito.verify(facialrecognitionlogService, Mockito.times(1)).save(ArgumentMatchers.any(FacialRecognitionLogDto.class));
        Mockito.verifyNoMoreInteractions(facialrecognitionlogService);
    }

    @Test
    public void update() throws Exception {
        Mockito.when(facialrecognitionlogService.update(ArgumentMatchers.any(), ArgumentMatchers.anyLong()))
                .thenReturn(FacialRecognitionLogBuilder.getDto());

        mockMvc.perform(
                        MockMvcRequestBuilders.put(ENDPOINT_URL + "/1")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(CustomUtils.asJsonString(FacialRecognitionLogBuilder.getDto())))
                .andExpect(MockMvcResultMatchers.status().isOk());

        Mockito.verify(facialrecognitionlogService, Mockito.times(1))
                .update(ArgumentMatchers.any(FacialRecognitionLogDto.class), ArgumentMatchers.anyLong());
        Mockito.verifyNoMoreInteractions(facialrecognitionlogService);
    }

    @Test
    public void delete() throws Exception {
        Mockito.doNothing().when(facialrecognitionlogService).deleteById(ArgumentMatchers.anyLong());

        mockMvc.perform(
                        MockMvcRequestBuilders.delete(ENDPOINT_URL + "/1")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(CustomUtils.asJsonString(FacialRecognitionLogBuilder.getIds())))
                .andExpect(MockMvcResultMatchers.status().isOk());

        Mockito.verify(facialrecognitionlogService, Mockito.times(1))
                .deleteById(Mockito.anyLong());
        Mockito.verifyNoMoreInteractions(facialrecognitionlogService);
    }
}
