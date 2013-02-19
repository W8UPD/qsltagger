# Deploying Latest QSLTagger Code

## Prereqs

To deploy qsltagger to http://qsl.w8upd.org/, you must have:

  - A valid account on the club web server
  - Sudo permissions on the club web server
  - Ansible installed on your computer.

## Doing the deploy

* `cd ./qsltagger/deploy`
* `time ansible-playbook -i qsltagger-servers -k -K deploy.yml`

The deploy can take anywhere from 1 to 5 minutes.
