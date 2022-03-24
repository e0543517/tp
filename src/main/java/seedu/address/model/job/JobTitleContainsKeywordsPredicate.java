package seedu.address.model.job;

import java.util.List;
import java.util.function.Predicate;

import seedu.address.commons.util.StringUtil;


public class JobTitleContainsKeywordsPredicate implements Predicate<Job> {
    private final List<String> keywords;

    public JobTitleContainsKeywordsPredicate(List<String> keywords) {
        this.keywords = keywords;
    }

    @Override
    public boolean test(Job job) {
        return keywords.stream()
                .anyMatch(keyword -> StringUtil.containsWordIgnoreCase(job.getJobTitle().jobTitle, keyword));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof JobTitleContainsKeywordsPredicate // instanceof handles nulls
                && keywords.equals(((JobTitleContainsKeywordsPredicate) other).keywords)); // state check
    }
}
