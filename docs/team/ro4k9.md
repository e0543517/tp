---
layout: page
title: Rosa Kang's Project Portfolio Page
---

### Project: ReCLIne

ReCLIne is a profile management desktop application that can be used by recruiters. It serves as a centralised location for recruiters to organise their applicant and job data. Users can interact with ReCLIne using a CLI, and it has a GUI created with JavaFX. It is written in Java, and has about 10kLoC.

Given below are my contributions to the project.

* **New Feature**: Added the ability to display applicant and job lists in ReCLIne GUI. [\#146](https://github.com/AY2122S2-CS2103T-W15-1/tp/pull/146)
    * What it does: displays applicant and job lists in tabs. Each list contains applicant card and job card that contains basic information of applicant and job respectively.
    * Justification: it is important to the application as it allows easy access to the applicant and job lists as well as applicant and job information.
    * Highlights: it required an understanding of how Ui, Model, and Logic components are interconnected and interact with one and other. It required an understanding of JavaFX and FXML.
    * Credit: applicantListPanel, JobListPanel, ApplicantCard, and JobCard are adopted from the implementation of PersonListPanel and PersonCard . Using a Tab Panel to display these two lists is an implementation of my own.

* **New Feature**: Added Info Panel. [\#97](https://github.com/AY2122S2-CS2103T-W15-1/tp/pull/97)
    * What it does: info Panel is a panel that display full description of applicant and job information. Once the user selects one of the items in either of the lists, it will display their full information.
    * Justification: it is user-friendly. Because Applicant and Job stores the number of attributes, which are often lengthy, having all the information within a applicant or job card can be overwhelming and inconvenient for the user to scroll through.
    * Highlight: it was challenging to find a way to retrieve applicant in the list card and transfer their information on to a different panel. It required further understanding of JavaFx on how to handle events.
    * Credit: the design of the info panel is an implementation on my own.

* **New Feature**: Added the ability to mark applicant. [\#89](https://github.com/AY2122S2-CS2103T-W15-1/tp/pull/89)
    * What it does: alows user to mark applicant status of an applicant stored in ReCLIne.
    * Justification: this feature is important because for recruiters, the user, keeping track of whether applicant is interviewed, rejected, accepted, or pending, is essential.
    * Credit: this was implemented together with @chanyijuan. I was in charge of creating a parser for markapplicant command. It is adopted from commands parsers in AB3.

* **New Feature**: Adding the ability to find job. [\#154](https://github.com/AY2122S2-CS2103T-W15-1/tp/pull/154)
    * What it does: allows the user to search for Jobs by either job title or job id.
    * Justification: This feature is essential. It allows the user to find the job they wish to find without having to scroll through the entire list. Also, as each applicant is associated with job id, it is important that the user is able to find the job given their id.
    * Credit: the implementation of findjob was adopted from AB3 findcommand.

* **New Feature**: Added the ability to switch to applicant tab in ReCLIne. [\#137](https://github.com/AY2122S2-CS2103T-W15-1/tp/pull/137) (note: listapplicant is a former command name for tabapplicant)
    * What it does: Allows the user to switch to a tab containing applicant list using CLI commands.
    * Justification: As our target user prefers to type over mouse click, having a CLI alternative for switching tabs is important.
    * Credits: tabapplicant is my own implementation

* **New Feature**: Added the ability to switch to job tab in ReCLIne. [\#137](https://github.com/AY2122S2-CS2103T-W15-1/tp/pull/137) (note: listjob is a former command name for tabjob)
    * What it does: Allows the user to switch to a tab containing job list using CLI commands.
    * Justification: As our target user prefers to type over mouse click, having a CLI alternative for switching tabs is important.
    * Credits: tabjob is my own implementation

* **New Feature**: Added new attribute class InterviewDate. [\#70](https://github.com/AY2122S2-CS2103T-W15-1/tp/pull/70)
    * What it does: Allows users to save the interview date.
    * Justification: This feature is useful in sortapplicant command, where the user can sort the applicant by interview date and date applied.


* **Code contributed**: [RepoSense link](https://nus-cs2103-ay2122s2.github.io/tp-dashboard/?search=ro4k9&breakdown=true)


* **Project management**:
    * Manage the issue tracker, open new issues and close ones that are completed.


* **Enhancements to existing features**:
    * Added a feature to clear both list and info panel via AB3 ClearCommand  [\#256](https://github.com/AY2122S2-CS2103T-W15-1/tp/pull/256)
    * Enhance and stylized the GUI features, commandbox, resultdisplay, ect, of AB3 [\#146](https://github.com/AY2122S2-CS2103T-W15-1/tp/pull/146)


* **Documentation**:
    * User Guide:
        * Added documentation for the features `markapplicant`, `findjob`, `tabapplicant`, and `tabjob` [\#109](https://github.com/AY2122S2-CS2103T-W15-1/tp/pull/109/files) [\#165](https://github.com/AY2122S2-CS2103T-W15-1/tp/pull/165)
        * Added UI guide in Quick Start section [\#274](https://github.com/AY2122S2-CS2103T-W15-1/tp/pull/274)
        * Added guide for MacOS user in Quick Start section [\#274](https://github.com/AY2122S2-CS2103T-W15-1/tp/pull/274)
    * Developer Guide:
        * Added Table of Content [\#300](https://github.com/AY2122S2-CS2103T-W15-1/tp/pull/300)
        * Added implementation details of the `markapplicant` feature, as well as the UML diagrams in that section. [\#136](https://github.com/AY2122S2-CS2103T-W15-1/tp/pull/136) [\#300](https://github.com/AY2122S2-CS2103T-W15-1/tp/pull/300)
        * Added documentation on how the Ui components of ReCLIne, as well as the UML diagrams in that section. [\#300](https://github.com/AY2122S2-CS2103T-W15-1/tp/pull/300)
        * Added instruction for manual testing for finding a job. [\#300](https://github.com/AY2122S2-CS2103T-W15-1/tp/pull/300)


* **Contributions to team-based tasks**:
    * Code quality: Looks after code quality, ensures adherence to coding standards.

* **Community**:
    * PRs reviewed (with non-trivial review comments): [\#74](https://github.com/AY2122S2-CS2103T-W15-1/tp/pull/74) [\#104](https://github.com/AY2122S2-CS2103T-W15-1/tp/pull/104) [\#87](https://github.com/AY2122S2-CS2103T-W15-1/tp/pull/87)
    * Helped to fix bug in group member's implementation [\#132](https://github.com/AY2122S2-CS2103T-W15-1/tp/pull/132) [\#153](hhttps://github.com/AY2122S2-CS2103T-W15-1/tp/pull/153)
