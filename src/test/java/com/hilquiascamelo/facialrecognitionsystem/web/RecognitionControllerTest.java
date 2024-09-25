package com.hilquiascamelo.facialrecognitionsystem.web;

import com.hilquiascamelo.facialrecognitionsystem.application.RecognitionService;
import org.bytedeco.opencv.opencv_core.Mat;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(RecognitionController.class)
class RecognitionControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean // Use @MockBean ao invés de @Mock
    private RecognitionService recognitionService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this); // Esta linha pode ser removida, pois não é necessária com @MockBean
    }

    @Test
    void testRecognize() throws Exception {
        // Mock do comportamento do serviço
        when(recognitionService.recognizeImage(any(Mat.class))).thenReturn(true);

        // Executa a requisição e verifica o resultado
        mockMvc.perform(post("/recognize")
                        .param("imagePath", "src/main/resources/imagens/imagem_de_teste.jpg")
                        .contentType(MediaType.APPLICATION_FORM_URLENCODED))
                .andExpect(status().isOk()); // Verifica se a resposta é 200 OK
    }
}
