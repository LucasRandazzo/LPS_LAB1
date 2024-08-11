import { useParams } from "react-router-dom"


const OtherDetails = () => {
    const { id } = useParams()
    return (
        <div>OtherDetails {id}</div>
    )
}

export default OtherDetails