package duplicatefilefinder.app.utils;

import org.junit.Test;

import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertThat;

/**
 * Created by Ravi Nain on 4/15/2018.
 */
public class FileUtilsTest {

    @Test
    public void itShouldReturnFileType() throws Exception {
        String fileName = "abcc.jpeg";
        String expected = "jpeg";

        assertThat(FileUtils.getFileType(fileName), equalTo(expected));
    }

    @Test
    public void itShouldReturnEmptyWhenFileNameEndsWithDot() throws Exception {
        String fileName = "abcc.";
        String expected = "";

        assertThat(FileUtils.getFileType(fileName), equalTo(expected));
    }

    @Test
    public void itShouldReturnEmptyWhenNoExtensionExists() throws Exception {
        String fileName = "abcc";
        String expected = "";

        assertThat(FileUtils.getFileType(fileName), equalTo(expected));
    }

    @Test
    public void itShouldReturnFileTypeWhenFileNameHasMultipleDots() throws Exception {
        String fileName = "abcc.test.txt";
        String expected = "txt";

        assertThat(FileUtils.getFileType(fileName), equalTo(expected));
    }

    @Test
    public void itShouldReturnFileNameWithoutExtension() throws Exception {
        String fileName = "abcc.jpeg";
        String expected = "abcc";

        assertThat(FileUtils.getFileNameWithoutExtension(fileName), equalTo(expected));
    }

    @Test
    public void itShouldReturnFileNameAsIsWhenFileNameEndsWithDot() throws Exception {
        String fileName = "abcc.";
        String expected = "abcc.";

        assertThat(FileUtils.getFileNameWithoutExtension(fileName), equalTo(expected));
    }

    @Test
    public void itShouldReturnFileNameWithoutExtensionWhenMultipleDotExists() throws Exception {
        String fileName = "abcc.test.ext";
        String expected = "abcc.test";

        assertThat(FileUtils.getFileNameWithoutExtension(fileName), equalTo(expected));
    }

    @Test
    public void itShouldReturnFileNameAsIsWhenNoExtensionExists() throws Exception {
        String fileName = "abcc";
        String expected = "abcc";

        assertThat(FileUtils.getFileNameWithoutExtension(fileName), equalTo(expected));
    }
}