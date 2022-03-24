package seedu.address.logic.parser.job;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_COMPANY_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_JOBTITLE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_JOB_POSITION;
import static seedu.address.logic.parser.CliSyntax.PREFIX_QUALIFICATION;
import static seedu.address.logic.parser.CliSyntax.PREFIX_SALARY;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.job.EditJob;
import seedu.address.logic.parser.ArgumentMultimap;
import seedu.address.logic.parser.ArgumentTokenizer;
import seedu.address.logic.parser.Parser;
import seedu.address.logic.parser.ParserUtil;
import seedu.address.logic.parser.exceptions.ParseException;

public class EditJobParser implements Parser<EditJob> {

    @Override
    public EditJob parse(String userInput) throws ParseException {
        requireNonNull(userInput);
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(userInput, PREFIX_JOBTITLE, PREFIX_COMPANY_NAME, PREFIX_ADDRESS,
                        PREFIX_QUALIFICATION, PREFIX_JOB_POSITION, PREFIX_SALARY);

        Index index;

        try {
            index = ParserUtil.parseIndex(argMultimap.getPreamble());
        } catch (ParseException pe) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, EditJob.MESSAGE_USAGE), pe);
        }

        EditJob.EditJobDescriptor editJobDescriptor = new EditJob.EditJobDescriptor();

        if (argMultimap.getValue(PREFIX_JOBTITLE).isPresent()) {
            editJobDescriptor.setJobTitle(ParserUtil.parseJobTitle(argMultimap.getValue(PREFIX_JOBTITLE).get()));
        }
        if (argMultimap.getValue(PREFIX_COMPANY_NAME).isPresent()) {
            editJobDescriptor.setCompanyName(ParserUtil.parseCompanyName(argMultimap
                    .getValue(PREFIX_COMPANY_NAME).get()));
        }
        if (argMultimap.getValue(PREFIX_ADDRESS).isPresent()) {
            editJobDescriptor.setAddress(ParserUtil.parseAddress(argMultimap.getValue(PREFIX_ADDRESS).get()));
        }
        if (argMultimap.getValue(PREFIX_QUALIFICATION).isPresent()) {
            editJobDescriptor.setQualification(ParserUtil
                    .parseQualification(argMultimap.getValue(PREFIX_QUALIFICATION).get()));
        }
        if (argMultimap.getValue(PREFIX_JOB_POSITION).isPresent()) {
            editJobDescriptor.setPosition(ParserUtil.parsePosition(argMultimap.getValue(PREFIX_JOB_POSITION).get()));
        }
        if (argMultimap.getValue(PREFIX_SALARY).isPresent()) {
            editJobDescriptor.setSalary(ParserUtil.parseSalary(argMultimap.getValue(PREFIX_SALARY).get()));
        }
        if (!editJobDescriptor.isAnyFieldEdited()) {
            throw new ParseException(EditJob.MESSAGE_NOT_EDITED);
        }

        return new EditJob(index, editJobDescriptor);
    }
}
