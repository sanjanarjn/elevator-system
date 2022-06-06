package com.lift.system.util;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.lift.system.exceptions.TestingException;
import com.lift.system.models.LiftSystemTestData;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.nio.file.Paths;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;

public class TestUtil {

    private static ObjectMapper mapper = new ObjectMapper();

    public static LiftSystemTestData getTestDataForInitialisation() throws TestingException {

        return getTestData(TestConstants.INITIAL_STATE_TEST_INPUT);
    }

    public static LiftSystemTestData getTestDataForInitialRequest() throws TestingException {

        return getTestData(TestConstants.INITIAL_REQUEST_TEST_INPUT);
    }

    public static List<LiftSystemTestData> getPickupRequestTestData(String folder) throws TestingException {

        List<LiftSystemTestData> testData = new ArrayList<>();
        try {
            URL inputFolder = TestUtil.class.getClassLoader().getResource(folder);
            File testDataFolder = new File(inputFolder.getFile());
            File[] testDataFiles = testDataFolder.listFiles();
            for (File testDataFile : testDataFiles) {
                testData.add(mapper.readValue(testDataFile, LiftSystemTestData.class));
            }
            return testData;
        } catch (IOException e) {
            throw new TestingException(MessageFormat.format(TestConstants.ERROR_WHILE_READING_TEST_INPUT, folder), e);
        }
    }

    private static LiftSystemTestData getTestData(String testFile) throws TestingException {
        try {
            InputStream inputFile = TestUtil.class.getClassLoader().getResourceAsStream(testFile);
            return mapper.readValue(inputFile, LiftSystemTestData.class);
        } catch (IOException e) {
            throw new TestingException(MessageFormat.format(TestConstants.ERROR_WHILE_READING_TEST_INPUT, testFile), e);
        }
    }
}
