import { Link } from "react-router-dom"

const Header = () => {
    return (
        <header>
            Header

            <nav>
                <Link to={'/'}>Home</Link>
                <Link to={'/other'}>Other</Link>
            </nav>
        </header>

    )
}

export default Header