package com.lift.system.util;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.lift.system.exceptions.TestingException;
import com.lift.system.models.LiftSystemTestData;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Paths;
import java.text.MessageFormat;

public class TestUtil {

    private static ObjectMapper mapper = new ObjectMapper();

    public static LiftSystemTestData getTestDataForInitialisation() throws TestingException {

        return getTestData(TestConstants.INITIAL_STATE_TEST_INPUT);
    }

    public static LiftSystemTestData getTestDataForInitialRequest() throws TestingException {

        return getTestData(TestConstants.INITIAL_STATE_TEST_INPUT);
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
