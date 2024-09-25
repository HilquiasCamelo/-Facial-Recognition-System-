package com.hilquiascamelo.facialrecognitionsystem.application;

import org.bytedeco.javacpp.DoublePointer;
import org.bytedeco.javacpp.IntPointer;
import org.bytedeco.opencv.global.opencv_imgcodecs;
import org.bytedeco.opencv.global.opencv_imgproc;
import org.bytedeco.opencv.opencv_core.Mat;
import org.bytedeco.opencv.opencv_core.MatVector;
import org.bytedeco.opencv.opencv_core.RectVector;
import org.bytedeco.opencv.opencv_face.LBPHFaceRecognizer;
import org.bytedeco.opencv.opencv_objdetect.CascadeClassifier;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class RecognitionService {

    private static final Logger logger = LoggerFactory.getLogger(RecognitionService.class);
    private static final String IMAGE_STORAGE_PATH = "src/main/resources/imagens";
    private static final double CONFIDENCE_THRESHOLD = 80.0;

    private final CascadeClassifier faceDetector;
    private final LBPHFaceRecognizer faceRecognizer;
    private final Map<Integer, String> labelToImageMap = new HashMap<>(); // Mapeia rótulos para nomes de arquivos

    public RecognitionService() {
        logger.info("Construtor do RecognitionService chamado.");
        this.faceDetector = initializeFaceDetector();
        this.faceRecognizer = initializeFaceRecognizer();
    }

    private CascadeClassifier initializeFaceDetector() {
        String xmlFilePath = getClass().getResource("/haarcascade_frontalface_default.xml").getPath();
        if (xmlFilePath != null) {
            CascadeClassifier classifier = new CascadeClassifier(xmlFilePath);
            logger.info("Detector de faces inicializado com sucesso.");
            return classifier;
        } else {
            logger.error("Arquivo haarcascade_frontalface_default.xml não encontrado.");
            throw new RuntimeException("Arquivo haarcascade_frontalface_default.xml não encontrado.");
        }
    }

    private LBPHFaceRecognizer initializeFaceRecognizer() {
        logger.info("Inicializando o reconhecedor de faces...");
        LBPHFaceRecognizer recognizer = LBPHFaceRecognizer.create();
        List<Mat> images = loadStoredImages();
        List<Integer> labels = new ArrayList<>();

        logger.info("Preparando os rótulos para as imagens carregadas. Total de imagens: {}", images.size());
        for (int i = 0; i < images.size(); i++) {
            labels.add(i); // Utiliza o índice como rótulo
            labelToImageMap.put(i, images.get(i).toString()); // Mapeia o rótulo para o nome da imagem
            logger.debug("Rótulo {} associado à imagem {}", i, labelToImageMap.get(i));
        }

        if (!images.isEmpty() && !labels.isEmpty()) {
            logger.info("Imagens e rótulos estão disponíveis para treinamento.");

            MatVector imageVector = new MatVector(images.size());
            for (int i = 0; i < images.size(); i++) {
                Mat grayImage = new Mat();
                opencv_imgproc.cvtColor(images.get(i), grayImage, opencv_imgproc.COLOR_BGR2GRAY);
                imageVector.put(i, grayImage);
            }

            int[] labelArray = labels.stream().mapToInt(j -> j).toArray();
            recognizer.train(imageVector, new Mat(labelArray));
            logger.info("Modelo de reconhecimento facial treinado com sucesso.");
        } else {
            logger.warn("Nenhuma imagem ou rótulo disponível para treinamento.");
        }

        logger.info("Reconhecedor de faces inicializado com sucesso.");
        return recognizer;
    }

    public boolean recognizeImage(Mat inputImage) {
        logger.info("Iniciando reconhecimento de imagem.");

        Mat grayImage = new Mat();
        opencv_imgproc.cvtColor(inputImage, grayImage, opencv_imgproc.COLOR_BGR2GRAY);

        RectVector faces = detectFaces(grayImage);

        if (faces.size() == 0) {
            logger.warn("Nenhuma face detectada na imagem de entrada.");
            return false;
        }

        Mat faceToRecognize = new Mat(grayImage, faces.get(0));

        IntPointer predictedLabel = new IntPointer(1);
        DoublePointer predictedConfidence = new DoublePointer(1);

        faceRecognizer.predict(faceToRecognize, predictedLabel, predictedConfidence);

        int label = predictedLabel.get(0);
        double confidence = predictedConfidence.get(0);

        logger.info("Predição realizada: rótulo = {}, confiança = {}", label, confidence);

        if (confidence >= CONFIDENCE_THRESHOLD) {
            logger.warn("Imagem não reconhecida. Confiança: {}", confidence);
            return false;
        }

        // Loga a imagem associada ao rótulo
        String associatedImage = labelToImageMap.get(label);
        logger.info("Imagem reconhecida com sucesso. Associada à imagem: {} com confiança: {}", associatedImage, confidence);

        return true;
    }

    private List<Mat> loadStoredImages() {
        List<Mat> images = new ArrayList<>();
        File directory = new File(IMAGE_STORAGE_PATH);

        if (directory.exists() && directory.isDirectory()) {
            File[] files = directory.listFiles((dir, name) -> name.endsWith(".jpg") || name.endsWith(".png"));
            if (files != null) {
                for (File file : files) {
                    Mat image = opencv_imgcodecs.imread(file.getAbsolutePath());
                    if (!image.empty()) {
                        images.add(image);
                        logger.info("Imagem carregada: {}", file.getName());
                    } else {
                        logger.warn("Falha ao carregar a imagem {}", file.getName());
                    }
                }
            } else {
                logger.warn("Nenhum arquivo de imagem encontrado no diretório.");
            }
        } else {
            logger.error("Diretório de armazenamento de imagens não encontrado: {}", IMAGE_STORAGE_PATH);
        }

        return images;
    }

    private RectVector detectFaces(Mat image) {
        RectVector faces = new RectVector();
        faceDetector.detectMultiScale(image, faces);
        return faces;
    }
}
