import RegistrationRepository from '../../repositories/RegistrationRepository';

import { useEffect, useState } from 'react';

const Registration = () => {
    const [registrations, setRegistrations] = useState([]);
    const [registrationId, setRegistrationId] = useState();
    const [subjects, setSubjects] = useState([]);
    useEffect(() => {
        const fetchRegistrations = async () => {
            try {
                const response = await RegistrationRepository.getAllRegistration();
                setRegistrations(response);
            } catch (error) {
                console.error("Error fetching registrations:", error);
                throw error;
            }
        };

        fetchRegistrations();
    }, []);
    const change = (newSubjects,selectedRegistrationId) => {
        setSubjects(newSubjects);
        setRegistrationId(selectedRegistrationId);
    }

    const remove =  (subjectId) => {
        const data = {
            "registrationId": registrationId,
            "subjectsIds":[subjectId]
        }
        console.log(data)
        RegistrationRepository.delete(data).then((response) => {
            console.log("Subject deleted:", response);
            setSubjects(subjects.filter((subject) => subject.id !== subjectId));
        }).catch((error) => {
            console.error("Error deleting subject:", error);
            throw error;
        })
    }
    return (
        <div>
            <h2>Registration List</h2>
            <table>
                <thead>
                    <tr>
                        <th>ID</th>
                        <th>Nome do curso</th>
                        <th>Nome do estudante</th>
                        <th>Actions</th>
                    </tr>
                </thead>
                <tbody>
                    {registrations.map((registration) => (
                        <tr key={registration.id}>
                            <td>{registration.id}</td>
                            <td>{registration.course.name}</td>
                            <td>{registration.student.name}</td>
                            <td><button onClick={() => {change(registration.subjects,registration.id)}}>See subjects</button></td>
                        </tr>
                    ))}
                </tbody>
            </table>
            <table>
                <thead>
                    <tr>
                        <th>ID</th>
                        <th>Nome da disciplina</th>
                        <th>situação</th>
                        <th>credito</th>
                        <th>valor</th>
                        <th>Action</th>
                    </tr>
                </thead>
                <tbody>
                    {subjects.map((subject) => (
                        <tr key={subject.id}>
                            <td>{subject.id}</td>
                            <td>{subject.discipline.name}</td>
                            <td>{subject.situation}</td>
                            <td>{subject.discipline.credits}</td>
                            <td>{subject.price}</td>
                            <td><button onClick={() => {remove(subject.id)}}>Delete </button></td>
                        </tr>
                    ))}
                </tbody>
            </table>
        </div>
    );
};
export default Registration;