package seedu.address.logic.commands.applicant;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DATEAPPLIED;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DATEINTERVIEW;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_JOB;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NRIC;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_QUALIFICATION;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_APPLICANTS;

import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.commons.util.CollectionUtil;
import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.applicant.Applicant;
import seedu.address.model.applicant.ApplicantStatus;
import seedu.address.model.applicant.DateApplied;
import seedu.address.model.applicant.InterviewDate;
import seedu.address.model.applicant.JobId;
import seedu.address.model.applicant.Nric;
import seedu.address.model.applicant.Qualification;
import seedu.address.model.person.Address;
import seedu.address.model.person.Email;
import seedu.address.model.person.Name;
import seedu.address.model.person.Phone;
import seedu.address.model.tag.Tag;

public class EditApplicant extends Command {

    public static final String COMMAND_WORD = "editapplicant";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Edits the details of the applicant identified "
            + "by the index number used in the displayed Applicant list.\n"
            + "Existing values will be overwritten by the input values.\n"
            + "Parameters: INDEX (must be a positive integer) "
            + "[" + PREFIX_NAME + "NAME] "
            + "[" + PREFIX_PHONE + "PHONE] "
            + "[" + PREFIX_EMAIL + "EMAIL] "
            + "[" + PREFIX_ADDRESS + "ADDRESS] "
            + "[" + PREFIX_NRIC + "NRIC] "
            + "[" + PREFIX_QUALIFICATION + "QUALIFICATION]\n"
            + "[" + PREFIX_DATEAPPLIED + "DATE APPLIED] "
            + "[" + PREFIX_JOB + "JOB ID] "
            + "[" + PREFIX_DATEINTERVIEW + "DATE OF INTERVIEW] "
            + "[" + PREFIX_TAG + "TAG]...\n"
            + "Example: " + COMMAND_WORD + " 1 "
            + PREFIX_PHONE + "91234567 "
            + PREFIX_QUALIFICATION + "Bachelor in Computing "
            + PREFIX_JOB + "1234";

    public static final String MESSAGE_NOT_IMPLEMENTED = "The EditApplicant feature is not completed yet.";
    public static final String MESSAGE_EDIT_APPLICANT_SUCCESS = "Edited Applicant: %1$s";
    public static final String MESSAGE_NOT_EDITED = "At least one field to edit must be provided.";
    public static final String MESSAGE_DUPLICATE_APPLICANT = "This Applicant already exists in the address book.";

    private final Index index;
    private final EditApplicantDescriptor editApplicantDescriptor;

    /**
     * Constructor for EditApplicant Class
     *
     * @param index of the applicant in the filtered applicant list to edit
     * @param editApplicantDescriptor details to edit the applicant with
     */
    public EditApplicant(Index index, EditApplicantDescriptor editApplicantDescriptor) {
        requireAllNonNull(index, editApplicantDescriptor);

        this.index = index;
        this.editApplicantDescriptor = new EditApplicantDescriptor(editApplicantDescriptor);
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Applicant> lastShownList = model.getFilteredApplicantList();

        if (index.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_APPLICANT_DISPLAYED_INDEX);
        }

        Applicant applicantToEdit = lastShownList.get(index.getZeroBased());
        Applicant editedApplicant = createEditedApplicant(applicantToEdit, editApplicantDescriptor);

        if (!applicantToEdit.isSameApplicant(editedApplicant) && model.hasApplicant(editedApplicant)) {
            throw new CommandException(MESSAGE_DUPLICATE_APPLICANT);
        }

        model.setApplicant(applicantToEdit, editedApplicant);
        model.updateFilteredApplicantList(PREDICATE_SHOW_ALL_APPLICANTS);
        return new CommandResult(String.format(MESSAGE_EDIT_APPLICANT_SUCCESS, editedApplicant));
    }

    /**
     * Creates and returns a {@code Applicant} with the details of {@code applicantToEdit}
     * edited with {@code editApplicantDescriptor}.
     */
    private static Applicant createEditedApplicant(Applicant applicantToEdit,
                                                EditApplicantDescriptor editApplicantDescriptor) {
        assert applicantToEdit != null;

        Name updatedName = editApplicantDescriptor.getName().orElse(applicantToEdit.getName());
        Phone updatedPhone = editApplicantDescriptor.getPhone().orElse(applicantToEdit.getPhone());
        Email updatedEmail = editApplicantDescriptor.getEmail().orElse(applicantToEdit.getEmail());
        Address updatedAddress = editApplicantDescriptor.getAddress().orElse(applicantToEdit.getAddress());
        Set<Tag> updatedTags = editApplicantDescriptor.getTags().orElse(applicantToEdit.getTags());

        DateApplied updatedDateApplied = editApplicantDescriptor.getDateApplied()
                .orElse(applicantToEdit.getDateApplied());
        Nric updatedNric = editApplicantDescriptor.getNric().orElse(applicantToEdit.getNric());
        Qualification updatedQualification = editApplicantDescriptor.getQualification()
                .orElse(applicantToEdit.getQualification());
        InterviewDate updatedInterviewDate = editApplicantDescriptor.getInterviewDate()
                .orElse(applicantToEdit.getInterviewDate());
        // TODO: Add Job update method as well
        JobId updatedJob = editApplicantDescriptor.getJobId().orElse(applicantToEdit.getJobId());
        ApplicantStatus applicantStatus = applicantToEdit.getApplicantStatus();

        return new Applicant(updatedName, updatedPhone, updatedEmail, updatedAddress, updatedTags, updatedDateApplied,
                updatedNric, updatedJob, updatedInterviewDate, updatedQualification, applicantStatus);
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof EditApplicant)) {
            return false;
        }

        // state check
        EditApplicant e = (EditApplicant) other;
        return index.equals(e.index)
                && editApplicantDescriptor.equals(e.editApplicantDescriptor);
    }

    /**
     * Stores the details to edit the applicant with. Each non-empty field value will replace the
     * corresponding field value of the applicant.
     */
    public static class EditApplicantDescriptor {
        private Name name;
        private Nric nric;
        private Phone phone;
        private Email email;
        private Address address;
        private Set<Tag> tags;
        private Qualification qualification;
        private DateApplied dateApplied;
        private JobId jobId;
        private InterviewDate interviewDate;

        public EditApplicantDescriptor() {}

        /**
         * Copy constructor.
         * A defensive copy of {@code tags} is used internally.
         */
        public EditApplicantDescriptor(EditApplicantDescriptor toCopy) {
            setName(toCopy.name);
            setPhone(toCopy.phone);
            setEmail(toCopy.email);
            setAddress(toCopy.address);
            setNric(toCopy.nric);
            setInterviewDate(toCopy.interviewDate);
            setQualification(toCopy.qualification);
            setDateApplied(toCopy.dateApplied);
            setJobId(toCopy.jobId);
            setTags(toCopy.tags);
        }

        /**
         * Returns true if at least one field is edited.
         */
        public boolean isAnyFieldEdited() {
            return CollectionUtil.isAnyNonNull(
                    name, phone, nric, email, address, interviewDate, qualification, dateApplied, tags, jobId);
        }

        public void setName(Name name) {
            this.name = name;
        }

        public Optional<Name> getName() {
            return Optional.ofNullable(name);
        }

        public void setPhone(Phone phone) {
            this.phone = phone;
        }

        public Optional<Phone> getPhone() {
            return Optional.ofNullable(phone);
        }

        public void setEmail(Email email) {
            this.email = email;
        }

        public Optional<Email> getEmail() {
            return Optional.ofNullable(email);
        }

        public void setAddress(Address address) {
            this.address = address;
        }

        public Optional<Address> getAddress() {
            return Optional.ofNullable(address);
        }

        public void setNric(Nric nric) {
            this.nric = nric;
        }

        public Optional<Nric> getNric() {
            return Optional.ofNullable(nric);
        }

        public void setQualification(Qualification qualification) {
            this.qualification = qualification;
        }

        public Optional<Qualification> getQualification() {
            return Optional.ofNullable(qualification);
        }

        public void setDateApplied(DateApplied dateApplied) {
            this.dateApplied = dateApplied;
        }

        public Optional<DateApplied> getDateApplied() {
            return Optional.ofNullable(dateApplied);
        }

        public void setJobId(JobId jobId) {
            this.jobId = jobId;
        }

        public Optional<JobId> getJobId() {
            return Optional.ofNullable(jobId);
        }

        public void setInterviewDate(InterviewDate interviewDate) {
            this.interviewDate = interviewDate;
        }

        public Optional<InterviewDate> getInterviewDate() {
            return Optional.ofNullable(interviewDate);
        }

        /**
         * Sets {@code tags} to this object's {@code tags}.
         * A defensive copy of {@code tags} is used internally.
         */
        public void setTags(Set<Tag> tags) {
            this.tags = (tags != null) ? new HashSet<>(tags) : null;
        }

        /**
         * Returns an unmodifiable tag set, which throws {@code UnsupportedOperationException}
         * if modification is attempted.
         * Returns {@code Optional#empty()} if {@code tags} is null.
         */
        public Optional<Set<Tag>> getTags() {
            return (tags != null) ? Optional.of(Collections.unmodifiableSet(tags)) : Optional.empty();
        }

        @Override
        public boolean equals(Object other) {
            // short circuit if same object
            if (other == this) {
                return true;
            }

            // instanceof handles nulls
            if (!(other instanceof EditApplicantDescriptor)) {
                return false;
            }

            // state check
            EditApplicantDescriptor e = (EditApplicantDescriptor) other;

            return getName().equals(e.getName())
                    && getPhone().equals(e.getPhone())
                    && getEmail().equals(e.getEmail())
                    && getAddress().equals(e.getAddress())
                    && getNric().equals(e.getNric())
                    && getDateApplied().equals(e.getDateApplied())
                    && getInterviewDate().equals(e.getInterviewDate())
                    && getQualification().equals(e.getQualification())
                    && getJobId().equals(e.getJobId())
                    && getTags().equals(e.getTags());
        }
    }
}
