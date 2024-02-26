using Restaurante.Models;
using System.Collections.Generic;

namespace Restaurante.Service
{
    public interface IPersonal
    {
        IEnumerable<TaPersonal> GetPersonal();

        TaPersonal ObtenerPorid(int id);

        void add(TaPersonal taPersonal);

        void modificar(TaPersonal taPersonal);

        void eleminar(int id);
    }
}
