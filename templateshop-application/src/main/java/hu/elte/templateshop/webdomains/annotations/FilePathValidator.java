package hu.elte.templateshop.webdomains.annotations;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.io.File;

public class FilePathValidator implements ConstraintValidator<FilePathValid, String> {

    @Override
    public void initialize(FilePathValid constraint) {
    }

    @Override
    public boolean isValid(String filePath, ConstraintValidatorContext cxt) {
        File file = new File("templates/" + filePath + ".zip");
        return file.exists() && !file.isDirectory();
    }
}
