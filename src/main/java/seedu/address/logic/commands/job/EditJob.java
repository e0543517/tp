package seedu.address.logic.commands.job;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_COMPANY_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_JOB;
import static seedu.address.logic.parser.CliSyntax.PREFIX_JOBTITLE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_JOB_POSITION;
import static seedu.address.logic.parser.CliSyntax.PREFIX_QUALIFICATION;
import static seedu.address.logic.parser.CliSyntax.PREFIX_SALARY;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_JOBS;

import java.util.List;
import java.util.Optional;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.commons.util.CollectionUtil;
import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.applicant.JobId;
import seedu.address.model.applicant.Qualification;
import seedu.address.model.job.CompanyName;
import seedu.address.model.job.Job;
import seedu.address.model.job.JobStatus;
import seedu.address.model.job.JobTitle;
import seedu.address.model.job.Position;
import seedu.address.model.job.Salary;
import seedu.address.model.person.Address;

public class EditJob extends Command {
    public static final String COMMAND_WORD = "editjob";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Edits the details of the job identified "
            + "by the index number used in the displayed Job list.\n"
            + "Existing values will be overwritten by the input values.\n"
            + "Parameters: INDEX (must be a positive integer) "
            + "[" + PREFIX_JOBTITLE + "JOB TITLE] "
            + "[" + PREFIX_COMPANY_NAME + "COMPANY NAME] "
            + "[" + PREFIX_ADDRESS + "ADDRESS] "
            + "[" + PREFIX_QUALIFICATION + "QUALIFICATION]\n"
            + "[" + PREFIX_JOB_POSITION + "POSITION] "
            + "[" + PREFIX_SALARY + "SALARY]\n"
            + "Example: " + COMMAND_WORD + " 1 "
            + PREFIX_COMPANY_NAME + "JP Morgan "
            + PREFIX_QUALIFICATION + "Bachelor in Computing "
            + PREFIX_JOB + "1234";

    public static final String MESSAGE_EDIT_JOB_SUCCESS = "Edited Job: %1$s";
    public static final String MESSAGE_NOT_EDITED = "At least one field to edit must be provided.";
    public static final String MESSAGE_DUPLICATE_JOB = "This Job already exists in the address book.";

    private final Index index;
    private final EditJobDescriptor editJobDescriptor;

    /**
     * Constructor for EditJob Class
     *
     * @param index of the job in the filtered job list to edit
     * @param editJobDescriptor details to edit the job with
     */
    public EditJob(Index index, EditJobDescriptor editJobDescriptor) {
        requireAllNonNull(index, editJobDescriptor);

        this.index = index;
        this.editJobDescriptor = new EditJobDescriptor(editJobDescriptor);
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Job> lastShownList = model.getFilteredJobList();

        if (index.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_JOB_DISPLAYED_INDEX);
        }

        Job jobToEdit = lastShownList.get(index.getZeroBased());
        Job editedJob = createEditedJob(jobToEdit, editJobDescriptor);

        if (!jobToEdit.isSameJob(editedJob) && model.hasJob(editedJob)) {
            throw new CommandException(MESSAGE_DUPLICATE_JOB);
        }

        model.setJob(jobToEdit, editedJob);
        model.updateFilteredJobList(PREDICATE_SHOW_ALL_JOBS);
        return new CommandResult(String.format(MESSAGE_EDIT_JOB_SUCCESS, editedJob));
    }

    /**
     * Creates and returns a {@code Job} with the details of {@code jobToEdit}
     * edited with {@code editJobDescriptor}.
     */
    private static Job createEditedJob(Job jobToEdit,
                                                   EditJobDescriptor editJobDescriptor) {
        assert jobToEdit != null;

        JobTitle updatedJobTitle = editJobDescriptor.getJobTitle().orElse(jobToEdit.getJobTitle());
        CompanyName updatedCompanyName = editJobDescriptor.getCompanyName().orElse(jobToEdit.getCompany());
        Address updatedAddress = editJobDescriptor.getAddress().orElse(jobToEdit.getAddress());
        Qualification updatedQualification = editJobDescriptor.getQualification().orElse(jobToEdit.getQualification());
        Position updatedPosition = editJobDescriptor.getPosition().orElse(jobToEdit.getPosition());
        Salary updatedSalary = editJobDescriptor.getSalary().orElse((jobToEdit.getSalary()));

        // Get the old values
        JobId updatedJobId = jobToEdit.getJobId();
        JobStatus updatedJobStatus = jobToEdit.getJobStatus();

        return new Job(updatedJobTitle, updatedCompanyName, updatedJobId, updatedAddress, updatedQualification,
                updatedJobStatus, updatedPosition, updatedSalary);
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof EditJob)) {
            return false;
        }

        // state check
        EditJob e = (EditJob) other;
        return index.equals(e.index)
                && editJobDescriptor.equals(e.editJobDescriptor);
    }


    /**
     * Stores the details to edit the job with. Each non-empty field value will replace the
     * corresponding field value of the job.
     */
    public static class EditJobDescriptor {
        private JobTitle jobTitle;
        private CompanyName companyName;
        private Address address;
        private Qualification qualification;
        private Position position;
        private Salary salary;

        public EditJobDescriptor() {}

        /**
         * Copy constructor.
         * A defensive copy of {@code tags} is used internally.
         */
        public EditJobDescriptor(EditJobDescriptor toCopy) {
            setJobTitle(toCopy.jobTitle);
            setCompanyName(toCopy.companyName);
            setAddress(toCopy.address);
            setQualification(toCopy.qualification);
            setPosition(toCopy.position);
            setSalary(toCopy.salary);
        }

        /**
         * Returns true if at least one field is edited.
         */
        public boolean isAnyFieldEdited() {
            return CollectionUtil.isAnyNonNull(
                    jobTitle, companyName, address, qualification, position, salary);
        }

        public void setJobTitle(JobTitle jobTitle) {
            this.jobTitle = jobTitle;
        }

        public Optional<JobTitle> getJobTitle() {
            return Optional.ofNullable(jobTitle);
        }

        public void setCompanyName(CompanyName companyName) {
            this.companyName = companyName;
        }

        public Optional<CompanyName> getCompanyName() {
            return Optional.ofNullable(companyName);
        }

        public void setAddress(Address address) {
            this.address = address;
        }

        public Optional<Address> getAddress() {
            return Optional.ofNullable(address);
        }

        public void setQualification(Qualification qualification) {
            this.qualification = qualification;
        }

        public Optional<Qualification> getQualification() {
            return Optional.ofNullable(qualification);
        }

        public void setPosition(Position position) {
            this.position = position;
        }

        public Optional<Position> getPosition() {
            return Optional.ofNullable(position);
        }

        public void setSalary(Salary salary) {
            this.salary = salary;
        }

        public Optional<Salary> getSalary() {
            return Optional.ofNullable(salary);
        }

        @Override
        public boolean equals(Object other) {
            // short circuit if same object
            if (other == this) {
                return true;
            }

            // instanceof handles nulls
            if (!(other instanceof EditJobDescriptor)) {
                return false;
            }

            // state check
            EditJobDescriptor e = (EditJobDescriptor) other;

            return getJobTitle().equals(e.getJobTitle())
                    && getCompanyName().equals(e.getCompanyName())
                    && getAddress().equals(e.getAddress())
                    && getQualification().equals(e.getQualification())
                    && getPosition().equals(e.getPosition())
                    && getSalary().equals(e.getSalary());
        }
    }

}
