package seedu.address.testutil;

import static seedu.address.logic.commands.CommandTestUtil.VALID_ADDRESS_DATA_ANALYSIS;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ADDRESS_PROJECT_MANAGER;
import static seedu.address.logic.commands.CommandTestUtil.VALID_COMPANY_NAME_DATA_ANALYSIS;
import static seedu.address.logic.commands.CommandTestUtil.VALID_COMPANY_NAME_PROJECT_MANAGER;
import static seedu.address.logic.commands.CommandTestUtil.VALID_JOB_ID_DATA_ANALYSIS;
import static seedu.address.logic.commands.CommandTestUtil.VALID_JOB_ID_PROJECT_MANAGER;
import static seedu.address.logic.commands.CommandTestUtil.VALID_JOB_STATUS_DATA_ANALYSIS;
import static seedu.address.logic.commands.CommandTestUtil.VALID_JOB_STATUS_PROJECT_MANAGER;
import static seedu.address.logic.commands.CommandTestUtil.VALID_JOB_TITLE_DATA_ANALYSIS;
import static seedu.address.logic.commands.CommandTestUtil.VALID_JOB_TITLE_PROJECT_MANAGER;
import static seedu.address.logic.commands.CommandTestUtil.VALID_POSITION_FULL_TIME;
import static seedu.address.logic.commands.CommandTestUtil.VALID_QUALIFICATION_DATA_ANALYSIS;
import static seedu.address.logic.commands.CommandTestUtil.VALID_QUALIFICATION_PROJECT_MANAGER;
import static seedu.address.logic.commands.CommandTestUtil.VALID_SALARY_HIGH_DATA_ANALYSIS;
import static seedu.address.logic.commands.CommandTestUtil.VALID_SALARY_HIGH_PROJECT_MANAGER;
import static seedu.address.logic.commands.CommandTestUtil.VALID_SALARY_LOW_DATA_ANALYSIS;
import static seedu.address.logic.commands.CommandTestUtil.VALID_SALARY_LOW_PROJECT_MANAGER;
import static seedu.address.testutil.TypicalApplicants.getTypicalApplicants;
import static seedu.address.testutil.TypicalPersons.getTypicalPersons;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import seedu.address.model.AddressBook;
import seedu.address.model.applicant.Applicant;
import seedu.address.model.job.Job;
import seedu.address.model.person.Person;

public class TypicalJobs {
    public static final Job SOFTWARE_ENGINEER = new JobBuilder().withJobTitle("Software Engineer")
            .withCompanyName("Google").withJobId("12332").withJobAddress("123 Google Road")
            .withJobQualification("Degree in Computer Science").withJobStatus("filled")
            .withJobPosition("ft").withJobSalary("5000", "6000").build();
    public static final Job ENGINEER = new JobBuilder().withJobTitle("Engineer")
            .withCompanyName("Visa").withJobId("1432").withJobAddress("123 Visa Road")
            .withJobQualification("Degree in Engineering").withJobStatus("filled")
            .withJobPosition("ft").withJobSalary("4000", "6000").build();
    public static final Job ACCOUNTANT = new JobBuilder().withJobTitle("Accountant")
            .withCompanyName("DBS").withJobId("143241").withJobAddress("194 DBS Road")
            .withJobQualification("Degree in Accounting").withJobStatus("vacant")
            .withJobPosition("ft").withJobSalary("5000", "8000").build();
    public static final Job CASHIER = new JobBuilder().withJobTitle("Cashier")
            .withCompanyName("NTUC").withJobId("1234").withJobAddress("194 NTUC Fairprice Road")
            .withJobQualification("No Requirement").withJobStatus("filled")
            .withJobPosition("ft").withJobSalary("2000", "3000").build();
    public static final Job SAFE_DISTANCING_AMBASSADOR = new JobBuilder().withJobTitle("Safe Distancing Ambassador")
            .withCompanyName("Ministry of Sustainability and Environment").withJobId("145")
            .withJobAddress("1 Safe Distance Road").withJobQualification("No Requirement").withJobStatus("vacant")
            .withJobPosition("pt").withJobSalary("10", "13").build();
    public static final Job FOOD_PACKER = new JobBuilder().withJobTitle("Food Packer")
            .withCompanyName("FitThree").withJobId("923").withJobAddress("194 Packing Road")
            .withJobQualification("No Requirement").withJobStatus("filled")
            .withJobPosition("pt").withJobSalary("10", "10").build();
    public static final Job WAITER = new JobBuilder().withJobTitle("Waiter")
            .withCompanyName("Ichiban").withJobId("3").withJobAddress("14 Waiting Table Road")
            .withJobQualification("No Requirement").withJobStatus("filled")
            .withJobPosition("pt").withJobSalary("8", "10").build();

    // Manually added - Job's details found in {@code CommandTestUtil}
    public static final Job DATA_ANALYSIS = new JobBuilder().withJobTitle(VALID_JOB_TITLE_DATA_ANALYSIS)
        .withCompanyName(VALID_COMPANY_NAME_DATA_ANALYSIS).withJobId(VALID_JOB_ID_DATA_ANALYSIS)
        .withJobAddress(VALID_ADDRESS_DATA_ANALYSIS).withJobQualification(VALID_QUALIFICATION_DATA_ANALYSIS)
        .withJobStatus(VALID_JOB_STATUS_DATA_ANALYSIS).withJobPosition(VALID_POSITION_FULL_TIME)
        .withJobSalary(VALID_SALARY_LOW_DATA_ANALYSIS, VALID_SALARY_HIGH_DATA_ANALYSIS).build();
    public static final Job PROJECT_MANAGER = new JobBuilder().withJobTitle(VALID_JOB_TITLE_PROJECT_MANAGER)
            .withCompanyName(VALID_COMPANY_NAME_PROJECT_MANAGER).withJobId(VALID_JOB_ID_PROJECT_MANAGER)
            .withJobAddress(VALID_ADDRESS_PROJECT_MANAGER).withJobQualification(VALID_QUALIFICATION_PROJECT_MANAGER)
            .withJobStatus(VALID_JOB_STATUS_PROJECT_MANAGER).withJobPosition(VALID_POSITION_FULL_TIME)
            .withJobSalary(VALID_SALARY_LOW_PROJECT_MANAGER, VALID_SALARY_HIGH_PROJECT_MANAGER).build();

    private TypicalJobs() {} // prevents instantiation

    /**
     * Returns an {@code AddressBook} with all the typical applicants.
     */
    public static AddressBook getTypicalAddressBook() {
        AddressBook ab = new AddressBook();
        for (Person person : getTypicalPersons()) {
            ab.addPerson(person);
        }
        for (Applicant applicant: getTypicalApplicants()) {
            ab.addApplicant(applicant);
        }

        for (Job job : getTypicalJobs()) {

            // TODO CHANGE THIS ONCE ADDRESSBOOK IS CHANGED
            ab.addJob(job);
        }
        return ab;
    }

    public static List<Job> getTypicalJobs() {
        return new ArrayList<>(Arrays.asList(SOFTWARE_ENGINEER, ENGINEER,
                ACCOUNTANT, CASHIER, SAFE_DISTANCING_AMBASSADOR, FOOD_PACKER, WAITER));

    }
}
