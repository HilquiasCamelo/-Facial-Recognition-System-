package com.hilquiascamelo.facialrecognitionsystem.domain.controller;

import ch.qos.logback.core.joran.util.beans.BeanUtil;
import com.hilquiascamelo.facialrecognitionsystem.domain.controller.CustomUtils;
import com.hilquiascamelo.facialrecognitionsystem.domain.controller.WorkingHoursController;
import com.hilquiascamelo.facialrecognitionsystem.domain.dto.WorkingHoursDto;
import com.hilquiascamelo.facialrecognitionsystem.domain.mapper.WorkingHoursMapper;
import com.hilquiascamelo.facialrecognitionsystem.domain.model.WorkingHours;
import com.hilquiascamelo.facialrecognitionsystem.domain.service.WorkingHoursService;
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
public class WorkingHoursControllerTest {
    private static final String ENDPOINT_URL = "/api/working-hours";

    @InjectMocks
    private WorkingHoursController workinghoursController;

    @Mock
    private WorkingHoursService workinghoursService;

    private MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders
                .standaloneSetup(workinghoursController)
                .build();
    }

    @Test
    public void findAllByPage() throws Exception {
        Page<WorkingHoursDto> page = new PageImpl<>(Collections.singletonList(WorkingHoursBuilder.getDto()));

        Mockito.when(workinghoursService.findByCondition(ArgumentMatchers.any(), ArgumentMatchers.any()))
                .thenReturn(page);

        ResultActions resultActions = mockMvc.perform(MockMvcRequestBuilders.get(ENDPOINT_URL)
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.data.content", Matchers.hasSize(1)));

        Mockito.verify(workinghoursService, Mockito.times(1)).findByCondition(ArgumentMatchers.any(), ArgumentMatchers.any());
        Mockito.verifyNoMoreInteractions(workinghoursService);
    }

    @Test
    public void getById() throws Exception {
        Mockito.when(workinghoursService.findById(ArgumentMatchers.anyLong()))
                .thenReturn(WorkingHoursBuilder.getDto());

        mockMvc.perform(MockMvcRequestBuilders.get(ENDPOINT_URL + "/1"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content()
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.jsonPath("$.id", Is.is(1)));

        Mockito.verify(workinghoursService, Mockito.times(1)).findById("1");
        Mockito.verifyNoMoreInteractions(workinghoursService);
    }

    @Test
    public void save() throws Exception {
        Mockito.when(workinghoursService.save(ArgumentMatchers.any(WorkingHoursDto.class)))
                .thenReturn(WorkingHoursBuilder.getDto());

        mockMvc.perform(
                        MockMvcRequestBuilders.post(ENDPOINT_URL)
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(CustomUtils.asJsonString(WorkingHoursBuilder.getDto())))
                .andExpect(MockMvcResultMatchers.status().isCreated());

        Mockito.verify(workinghoursService, Mockito.times(1)).save(ArgumentMatchers.any(WorkingHoursDto.class));
        Mockito.verifyNoMoreInteractions(workinghoursService);
    }

    @Test
    public void update() throws Exception {
        Mockito.when(workinghoursService.update(ArgumentMatchers.any(), ArgumentMatchers.anyLong()))
                .thenReturn(WorkingHoursBuilder.getDto());

        mockMvc.perform(
                        MockMvcRequestBuilders.put(ENDPOINT_URL + "/1")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(CustomUtils.asJsonString(WorkingHoursBuilder.getDto())))
                .andExpect(MockMvcResultMatchers.status().isOk());

        Mockito.verify(workinghoursService, Mockito.times(1))
                .update(ArgumentMatchers.any(WorkingHoursDto.class), ArgumentMatchers.anyLong());
        Mockito.verifyNoMoreInteractions(workinghoursService);
    }

    @Test
    public void delete() throws Exception {
        Mockito.doNothing().when(workinghoursService).deleteById(ArgumentMatchers.anyLong());

        mockMvc.perform(
                        MockMvcRequestBuilders.delete(ENDPOINT_URL + "/1")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(CustomUtils.asJsonString(WorkingHoursBuilder.getIds())))
                .andExpect(MockMvcResultMatchers.status().isOk());

        Mockito.verify(workinghoursService, Mockito.times(1))
                .deleteById(Mockito.anyLong());
        Mockito.verifyNoMoreInteractions(workinghoursService);
    }
}
