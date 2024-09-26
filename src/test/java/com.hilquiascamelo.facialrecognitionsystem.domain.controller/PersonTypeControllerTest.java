package com.hilquiascamelo.facialrecognitionsystem.domain.controller;

import ch.qos.logback.core.joran.util.beans.BeanUtil;
import com.hilquiascamelo.facialrecognitionsystem.domain.controller.CustomUtils;
import com.hilquiascamelo.facialrecognitionsystem.domain.controller.PersonTypeController;
import com.hilquiascamelo.facialrecognitionsystem.domain.dto.PersonTypeDto;
import com.hilquiascamelo.facialrecognitionsystem.domain.mapper.PersonTypeMapper;
import com.hilquiascamelo.facialrecognitionsystem.domain.model.PersonType;
import com.hilquiascamelo.facialrecognitionsystem.domain.service.PersonTypeService;
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
public class PersonTypeControllerTest {
    private static final String ENDPOINT_URL = "/api/person-type";

    @InjectMocks
    private PersonTypeController persontypeController;

    @Mock
    private PersonTypeService persontypeService;

    private MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders
                .standaloneSetup(persontypeController)
                .build();
    }

    @Test
    public void findAllByPage() throws Exception {
        Page<PersonTypeDto> page = new PageImpl<>(Collections.singletonList(PersonTypeBuilder.getDto()));

        Mockito.when(persontypeService.findByCondition(ArgumentMatchers.any(), ArgumentMatchers.any()))
                .thenReturn(page);

        ResultActions resultActions = mockMvc.perform(MockMvcRequestBuilders.get(ENDPOINT_URL)
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.data.content", Matchers.hasSize(1)));

        Mockito.verify(persontypeService, Mockito.times(1)).findByCondition(ArgumentMatchers.any(), ArgumentMatchers.any());
        Mockito.verifyNoMoreInteractions(persontypeService);
    }

    @Test
    public void getById() throws Exception {
        Mockito.when(persontypeService.findById(ArgumentMatchers.anyLong()))
                .thenReturn(PersonTypeBuilder.getDto());

        mockMvc.perform(MockMvcRequestBuilders.get(ENDPOINT_URL + "/1"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content()
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.jsonPath("$.id", Is.is(1)));

        Mockito.verify(persontypeService, Mockito.times(1)).findById("1");
        Mockito.verifyNoMoreInteractions(persontypeService);
    }

    @Test
    public void save() throws Exception {
        Mockito.when(persontypeService.save(ArgumentMatchers.any(PersonTypeDto.class)))
                .thenReturn(PersonTypeBuilder.getDto());

        mockMvc.perform(
                        MockMvcRequestBuilders.post(ENDPOINT_URL)
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(CustomUtils.asJsonString(PersonTypeBuilder.getDto())))
                .andExpect(MockMvcResultMatchers.status().isCreated());

        Mockito.verify(persontypeService, Mockito.times(1)).save(ArgumentMatchers.any(PersonTypeDto.class));
        Mockito.verifyNoMoreInteractions(persontypeService);
    }

    @Test
    public void update() throws Exception {
        Mockito.when(persontypeService.update(ArgumentMatchers.any(), ArgumentMatchers.anyLong()))
                .thenReturn(PersonTypeBuilder.getDto());

        mockMvc.perform(
                        MockMvcRequestBuilders.put(ENDPOINT_URL + "/1")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(CustomUtils.asJsonString(PersonTypeBuilder.getDto())))
                .andExpect(MockMvcResultMatchers.status().isOk());

        Mockito.verify(persontypeService, Mockito.times(1))
                .update(ArgumentMatchers.any(PersonTypeDto.class), ArgumentMatchers.anyLong());
        Mockito.verifyNoMoreInteractions(persontypeService);
    }

    @Test
    public void delete() throws Exception {
        Mockito.doNothing().when(persontypeService).deleteById(ArgumentMatchers.anyLong());

        mockMvc.perform(
                        MockMvcRequestBuilders.delete(ENDPOINT_URL + "/1")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(CustomUtils.asJsonString(PersonTypeBuilder.getIds())))
                .andExpect(MockMvcResultMatchers.status().isOk());

        Mockito.verify(persontypeService, Mockito.times(1))
                .deleteById(Mockito.anyLong());
        Mockito.verifyNoMoreInteractions(persontypeService);
    }
}
