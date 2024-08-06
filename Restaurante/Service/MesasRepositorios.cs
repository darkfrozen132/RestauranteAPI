using Restaurante.Models;

namespace Restaurante.Service
{
    public class MesasRepositorios : IMesas

    {
        private Conexion conex = new Conexion();
        public void add(TaMesa taMesa)
        {
             conex.TaMesas.Add(taMesa);
            conex.SaveChanges();    
        }

        public void eleminar(int id)
        {
            conex.TaMesas.Remove(ObtenerPorid(id));
            conex.SaveChanges();    
        }

        public IEnumerable<TaMesa> GetMesas()
        {
            return conex.TaMesas;
        }

        public void modificar(TaMesa taMesa)
        {
            var obj = conex.TaMesas.Where(tpro => tpro.IdMesa == taMesa.IdMesa)
                                 .FirstOrDefault();
    
            obj.CantidadAsientos = taMesa.CantidadAsientos;
            obj.Reservado = taMesa.Reservado;
            obj.Descripcion = taMesa.Descripcion;
            obj.Reservado = taMesa.Reservado;
            obj.TaPedidos = taMesa.TaPedidos;
            obj.dLeft = taMesa.dLeft;
            obj.dUp = taMesa.dUp;
            conex.SaveChanges();
        }

        public TaMesa ObtenerPorid(int id)
        {
            return conex.TaMesas.Where(tpro => tpro.IdMesa == id).FirstOrDefault();
        }
    }
}
