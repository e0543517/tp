package seedu.address.testutil;

import seedu.address.logic.commands.job.EditJob.EditJobDescriptor;
import seedu.address.model.applicant.Qualification;
import seedu.address.model.job.CompanyName;
import seedu.address.model.job.Job;
import seedu.address.model.job.JobTitle;
import seedu.address.model.job.Position;
import seedu.address.model.job.Salary;
import seedu.address.model.person.Address;

public class EditJobDescriptorBuilder {
    private EditJobDescriptor descriptor;

    public EditJobDescriptorBuilder() {
        descriptor = new EditJobDescriptor();
    }

    public EditJobDescriptorBuilder(EditJobDescriptor descriptor) {
        this.descriptor = new EditJobDescriptor(descriptor);
    }

    /**
     * Returns an {@code EditJobDescriptor} with fields containing {@code Job}'s details
     */
    public EditJobDescriptorBuilder(Job job) {
        descriptor = new EditJobDescriptor();

        descriptor.setJobTitle(job.getJobTitle());
        descriptor.setCompanyName(job.getCompany());
        descriptor.setAddress(job.getAddress());
        descriptor.setQualification(job.getQualification());
        descriptor.setPosition(job.getPosition());
        descriptor.setSalary(job.getSalary());
    }

    /**
     * Sets the {@code JobTitle} of the {@code EditJobDescriptor} that we are building.
     */
    public EditJobDescriptorBuilder withJobTitle(String jobTitle) {
        descriptor.setJobTitle(new JobTitle(jobTitle));
        return this;
    }

    /**
     * Sets the {@code CompanyName} of the {@code EditJobDescriptor} that we are building.
     */
    public EditJobDescriptorBuilder withCompanyName(String companyName) {
        descriptor.setCompanyName(new CompanyName(companyName));
        return this;
    }

    /**
     * Sets the {@code Address} of the {@code EditJobDescriptor} that we are building.
     */
    public EditJobDescriptorBuilder withAddress(String address) {
        descriptor.setAddress(new Address(address));
        return this;
    }

    /**
     * Sets the {@code Qualification} of the {@code EditJobDescriptor} that we are building.
     */
    public EditJobDescriptorBuilder withQualification(String qualification) {
        descriptor.setQualification(new Qualification(qualification));
        return this;
    }

    /**
     * Sets the {@code Position} of the {@code EditJobDescriptor} that we are building.
     */
    public EditJobDescriptorBuilder withPosition(String position) {
        descriptor.setPosition(new Position(position));
        return this;
    }

    /**
     * Sets the {@code Salary} of the {@code EditJobDescriptor} that we are building.
     */
    public EditJobDescriptorBuilder withSalary(String salaryLow, String salaryHigh) {
        descriptor.setSalary(new Salary(salaryLow, salaryHigh));
        return this;
    }


    public EditJobDescriptor build() {
        return descriptor;
    }
}
